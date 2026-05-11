package com.example.kreedaprernascout

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class AnalyticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvBest = findViewById<TextView>(R.id.tvBest)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "student-db"
        )
            .allowMainThreadQueries()
            .build()

        val studentDao = db.studentDao()
        val performanceDao = db.performanceDao()

        // TOTAL ATHLETES
        val students = studentDao.getAll()
        tvTotal.text = students.size.toString()

        // BEST PERFORMANCE

        val leaderboard = performanceDao.getLeaderboard()

        if (leaderboard.isNotEmpty()) {

            val top = leaderboard[0]

            tvBest.text =
                "${top.name} - ${top.time} sec"

        } else {

            tvBest.text = "No trials yet"
        }
    }
}