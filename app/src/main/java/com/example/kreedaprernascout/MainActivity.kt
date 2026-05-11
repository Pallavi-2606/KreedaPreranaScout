package com.example.kreedaprernascout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var dao: StudentDao
    private lateinit var adapter: StudentAdapter
    private var selectedStudent: Student? = null
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.etName)
        val age = findViewById<EditText>(R.id.etAge)
        val sport = findViewById<EditText>(R.id.etSport)
        val btn = findViewById<Button>(R.id.btnSave)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val search = findViewById<EditText>(R.id.etSearch)
        val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAdd)
        val emptyText = findViewById<TextView>(R.id.tvEmpty)
        val spinner = findViewById<Spinner>(R.id.spinnerSport)
        firestore = FirebaseFirestore.getInstance()


        recyclerView.layoutManager = LinearLayoutManager(this)

        // DATABASE
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "student-db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        dao = db.studentDao()

        // SPINNER DATA
        val sports = listOf("All", "Cricket", "Football", "Running", "Tennis")
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            sports
        )
        spinner.adapter = spinnerAdapter

        // LOAD DATA FUNCTION
        fun loadData(query: String = "", selectedSport: String = "All") {

            var list = dao.getAll()

            // 🔍 SEARCH
            if (query.isNotEmpty()) {
                list = list.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }

            // 🏃 FILTER
            if (selectedSport != "All") {
                list = list.filter {
                    it.sport.equals(selectedSport, ignoreCase = true)
                }
            }

            // EMPTY STATE
            if (list.isEmpty()) {
                emptyText.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                emptyText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }

            adapter = StudentAdapter(
                list,
                onClick = { student ->
                    selectedStudent = student
                    name.setText(student.name)
                    age.setText(student.age.toString())
                    sport.setText(student.sport)
                    btn.text = "UPDATE"
                },

                onLongClick = { student ->
                    val intent = Intent(this, TrialActivity::class.java)
                    intent.putExtra("studentId", student.id)
                    startActivity(intent)
                },

                onDeleteClick = { student ->
                    dao.delete(student)
                    loadData(search.text.toString(), spinner.selectedItem.toString())

                    Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO") {
                            dao.insert(student)
                            loadData(search.text.toString(), spinner.selectedItem.toString())
                        }.show()
                }
            )

            recyclerView.adapter = adapter
        }

        // INITIAL LOAD
        loadData()

        // SEARCH LISTENER
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadData(s.toString(), spinner.selectedItem.toString())
            }
        })

        // SPINNER LISTENER
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                loadData(search.text.toString(), sports[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // SAVE / UPDATE
        btn.setOnClickListener {

                val n = name.text.toString()
                val a = age.text.toString()
                val s = sport.text.toString()

                if (n.isEmpty() || a.isEmpty() || s.isEmpty()) {
                    Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // FIREBASE DATA
                val athlete = hashMapOf(
                    "name" to n,
                    "age" to a,
                    "sport" to s
                )

                firestore.collection("athletes")
                    .add(athlete)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Saved to Firebase", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Firebase Error", Toast.LENGTH_SHORT).show()
                    }

                // ROOM DATABASE
                if (selectedStudent == null) {

                    dao.insert(Student(name = n, age = a.toInt(), sport = s))

                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()

                } else {

                    selectedStudent?.let {
                        it.name = n
                        it.age = a.toInt()
                        it.sport = s

                        dao.update(it)
                    }

                    Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
                }

                // RESET
                selectedStudent = null
                btn.text = "SAVE ATHLETE"

                // CLEAR
                name.text.clear()
                age.text.clear()
                sport.text.clear()

                loadData(search.text.toString(), spinner.selectedItem.toString())
        }

        // FAB → CLEAR FORM
        fab.setOnClickListener {
            selectedStudent = null
            btn.text = "SAVE ATHLETE"

            name.text.clear()
            age.text.clear()
            sport.text.clear()

            name.requestFocus()

            Toast.makeText(this, "Add new student", Toast.LENGTH_SHORT).show()
        }
    }
}