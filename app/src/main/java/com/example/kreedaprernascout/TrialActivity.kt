package com.example.kreedaprernascout

import android.os.Bundle
import android.os.SystemClock
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class TrialActivity : AppCompatActivity() {

    private var isRunning = false
    private lateinit var chronometer: Chronometer
    private lateinit var performanceDao: PerformanceDao
    private var studentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trial)

        studentId = intent.getIntExtra("studentId", -1)

        if (studentId == -1) {
            Toast.makeText(this, "Error: No Student Selected", Toast.LENGTH_LONG).show()
            finish()
        }

        chronometer = findViewById(R.id.chronometer)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "student-db"
        ).allowMainThreadQueries().build()

        performanceDao = db.performanceDao()

        btnStart.setOnClickListener {
            if (!isRunning) {
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                isRunning = true
            }
        }

        btnStop.setOnClickListener {
            if (isRunning) {
                chronometer.stop()
                isRunning = false
            }
        }

        btnSave.setOnClickListener {

            val timeText = chronometer.text.toString()
            val parts = timeText.split(":")
            val minutes = parts[0].toInt()
            val seconds = parts[1].toInt()

            val totalSeconds = minutes * 60 + seconds

            performanceDao.insert(
                Performance(
                    studentId = studentId,
                    testType = "SPRINT",
                    value = totalSeconds.toDouble(),
                    date = System.currentTimeMillis()
                )
            )

            Toast.makeText(this, "Saved: $totalSeconds sec", Toast.LENGTH_SHORT).show()

            finish() // ✅ go back
        }
    }
}