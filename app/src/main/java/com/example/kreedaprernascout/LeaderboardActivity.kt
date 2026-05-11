package com.example.kreedaprernascout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class LeaderboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLeaderboard)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // DATABASE
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "student-db"
        ).allowMainThreadQueries().build()

        val studentDao = db.studentDao()
        val performanceDao = db.performanceDao()

        val students = studentDao.getAll()

        val leaderboardList = mutableListOf<LeaderboardItem>()

        // GET LATEST PERFORMANCE OF EACH STUDENT
        students.forEach { student ->

            val latest = performanceDao.getLatestByStudent(student.id)

            if (latest != null) {

                leaderboardList.add(
                    LeaderboardItem(
                        name = student.name,
                        time = latest.value
                    )
                )
            }
        }

        // SORT BY FASTEST TIME
        leaderboardList.sortBy { it.time }

        // ADAPTER
        recyclerView.adapter = LeaderboardAdapter(leaderboardList)
    }
}