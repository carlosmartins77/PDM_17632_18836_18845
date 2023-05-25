package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.myteamspage.R

class SetNotifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_notifications)

        val progressBar = findViewById<ProgressBar>(R.id.set_notifications_progress_bar)
        val arrowBack = findViewById<ImageView>(R.id.set_notifications_arrow_icon)

        val continueBtn = findViewById<Button>(R.id.set_notifications_button2)

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 50

        arrowBack.setOnClickListener {
            val intent = Intent(this@SetNotifications, Create_your_team_register::class.java)
            startActivity(intent)
        }

        continueBtn.setOnClickListener {
            val intent = Intent(this@SetNotifications, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}