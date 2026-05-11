package com.example.kreedaprernascout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.Toast

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val athletes = findViewById<CardView>(R.id.cardAthletes)
        val leaderboard = findViewById<CardView>(R.id.cardLeaderboard)
        val trial = findViewById<CardView>(R.id.cardTrial)
        val analytics = findViewById<CardView>(R.id.cardAnalytics)

        athletes.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        leaderboard.setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }

        trial.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        analytics.setOnClickListener {
            Toast.makeText(
                this,
                "Analytics Coming Soon",
                Toast.LENGTH_SHORT
            ).show()
        }

        analytics.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }
    }
}