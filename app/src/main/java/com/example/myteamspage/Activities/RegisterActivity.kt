package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.myteamspage.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        passwordEditText = findViewById(R.id.passwordText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordText)
        val continueButton = findViewById<Button>(R.id.registerContinueBtn)
        val emailEt = findViewById<EditText>(R.id.registerEmailEt)
        val arrowBack = findViewById<ImageView>(R.id.register_arrow_icon)

        val progressBar = findViewById<ProgressBar>(R.id.register_progress_bar)

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 75


        continueButton.setOnClickListener {
            val emailTemp = emailEt.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if(emailTemp.matches(Regex("/^\\S+@\\S+\\.\\S+\$/")))
            {
                val email = emailTemp
            }
            val intent = Intent(this@RegisterActivity, CompleteYourProfile::class.java)
            startActivity(intent)
        }

        arrowBack.setOnClickListener {
            val intent = Intent(this@RegisterActivity, Create_your_team_register::class.java)
            startActivity(intent)
        }
    }
}