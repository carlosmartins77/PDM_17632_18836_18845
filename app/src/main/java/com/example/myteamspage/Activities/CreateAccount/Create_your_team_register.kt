package com.example.myteamspage.Activities.CreateAccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.myteamspage.Activities.Logo_KickOff
import com.example.myteamspage.R

class Create_your_team_register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_your_team_register)

        val progressBar = findViewById<ProgressBar>(R.id.create_your_team_register_progress_bar)
        val arrowBack = findViewById<ImageView>(R.id.create_your_team_register_arrow_icon)

        val skipBtn = findViewById<Button>(R.id.create_your_team_register_button1)
        val continueBtn = findViewById<Button>(R.id.create_your_team_register_button2)


        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 25

        arrowBack.setOnClickListener {
            val intent = Intent(this@Create_your_team_register, Logo_KickOff::class.java)
            startActivity(intent)
        }

        skipBtn.setOnClickListener {
            val intent = Intent(this@Create_your_team_register, RegisterActivity::class.java)
            startActivity(intent)
        }

        continueBtn.setOnClickListener {
            val intent = Intent(this@Create_your_team_register, SetNotifications::class.java)
            startActivity(intent)
        }
    }
}
