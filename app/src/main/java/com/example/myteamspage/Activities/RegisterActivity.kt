package com.example.myteamspage.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myteamspage.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val continueButton = findViewById<Button>(R.id.registerContinueBtn)
        val emailEt = findViewById<EditText>(R.id.registerEmailEt)
        val passwordEt = findViewById<EditText>(R.id.registerPasswordEt)
        val confirmPasswordEt = findViewById<EditText>(R.id.registerConfirmPasswordEt)

        continueButton.setOnClickListener {
            val emailTemp = emailEt.text.toString()
            val password = passwordEt.text.toString()
            val confirmPassword = confirmPasswordEt.text.toString()

            if(emailTemp.matches(Regex("/^\\S+@\\S+\\.\\S+\$/")))
            {
                val email = emailTemp
            }


        }
    }
}