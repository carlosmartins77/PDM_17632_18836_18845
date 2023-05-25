package com.example.myteamspage.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.myteamspage.R

class CompleteYourProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_your_profile)

        val progressBar = findViewById<ProgressBar>(R.id.complete_your_profile_progress_bar)

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 100
    }
}