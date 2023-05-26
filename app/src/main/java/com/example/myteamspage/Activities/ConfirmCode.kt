package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myteamspage.R

class ConfirmCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_code)

        val continueBtn = findViewById<Button>(R.id.continueAddCodeBtn)

        continueBtn.setOnClickListener {
            val intent = Intent(this@ConfirmCode, CreateNewPassword::class.java)
            startActivity(intent)
        }
    }
}