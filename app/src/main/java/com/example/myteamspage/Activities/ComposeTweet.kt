package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myteamspage.Activities.Account.PersonalInfo
import com.example.myteamspage.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ComposeTweet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_tweet)

        val arrowBack = findViewById<ImageView>(R.id.compose_tweet_arrow_icon)

        arrowBack.setOnClickListener {
            val intent = Intent(this@ComposeTweet, PublicationScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}