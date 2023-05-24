package com.example.myteamspage.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myteamspage.R

class Logo_KickOff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_kick_off)

        val getStarted = findViewById<Button>(R.id.logo_kick_off_button1)

        val alreadyHaveAccount = findViewById<Button>(R.id.logo_kick_off_button2)

        getStarted.setOnClickListener{

        }

        alreadyHaveAccount.setOnClickListener{

        }
    }
}