package com.example.kreedaprernascout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val list: List<Student>,
    private val onClick: (Student) -> Unit,
    private val onLongClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val details: TextView = view.findViewById(R.id.tvDetails)
        val deleteBtn: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = list[position]

        holder.name.text = student.name

        // 🔥 GET TIME FROM DATABASE
        val db = androidx.room.Room.databaseBuilder(
            holder.itemView.context,
            AppDatabase::class.java,
            "student-db"
        ).allowMainThreadQueries().build()

        val performanceDao = db.performanceDao()

        val latest = performanceDao.getLatestByStudent(student.id)

        // ✅ DEFINE timeText HERE
        val timeText = if (latest != null) {
            "${latest.value} sec"
        } else {
            "No trial"
        }

        holder.details.text =
            "Age: ${student.age} | Sport: ${student.sport} | Time: $timeText"

        // CLICK → EDIT
        holder.itemView.setOnClickListener {
            onClick(student)
        }

        // LONG CLICK → START TRIAL
        holder.itemView.setOnLongClickListener {
            onLongClick(student)
            true
        }

        // DELETE
        holder.deleteBtn.setOnClickListener {
            onDeleteClick(student)
        }
    }
}