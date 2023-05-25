package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.myteamspage.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val continueButton = findViewById<Button>(R.id.registerContinueBtn)
        val emailEt = findViewById<EditText>(R.id.registerEmailEt)
        val passwordEt = findViewById<EditText>(R.id.registerPasswordEt)
        val confirmPasswordEt = findViewById<EditText>(R.id.registerConfirmPasswordEt)
        val arrowBack = findViewById<ImageView>(R.id.register_arrow_icon)

        val progressBar = findViewById<ProgressBar>(R.id.register_progress_bar)

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 75


        continueButton.setOnClickListener {
            val emailTemp = emailEt.text.toString()
            val password = passwordEt.text.toString()
            val confirmPassword = confirmPasswordEt.text.toString()

            if(emailTemp.matches(Regex("/^\\S+@\\S+\\.\\S+\$/")))
            {
                val email = emailTemp
            }
        }

        arrowBack.setOnClickListener {
            val intent = Intent(this@RegisterActivity, Create_your_team_register::class.java)
            startActivity(intent)
        }
    }
}