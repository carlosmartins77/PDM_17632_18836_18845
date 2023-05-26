package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myteamspage.R

class ForgotPWD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pwd)

        val continueBtn = findViewById<Button>(R.id.continueForgotPWDBtn)

        continueBtn.setOnClickListener {
            val intent = Intent(this@ForgotPWD, ConfirmCode::class.java)
            startActivity(intent)
        }
    }
}