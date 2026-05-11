package com.example.kreedaprernascout

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val register = findViewById<TextView>(R.id.tvRegister)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)

        btnLogin.setOnClickListener {

            val savedUser = prefs.getString("username", null)
            val savedPass = prefs.getString("password", null)

            if (username.text.toString() == savedUser &&
                password.text.toString() == savedPass
            ) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, DashboardActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}