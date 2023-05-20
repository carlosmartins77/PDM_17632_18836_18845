package com.example.myteamspage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val emailEt = findViewById<EditText>(R.id.emailEt)
        val passwordEt = findViewById<EditText>(R.id.passwordEt)

        loginBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val passwordTemp = passwordEt.text.toString()

            if(passwordTemp.length < 6) {
                Toast.makeText(applicationContext,"PASSWORD TOO SHORT!",Toast.LENGTH_LONG).show()
            }
            else  {
                val password = passwordTemp
                // Start Schedule Game Activitie
                val intent = Intent(this@LoginActivity, ScheduleGame::class.java)
                startActivity(intent)
            }

            println("XD")

        }

    }
}