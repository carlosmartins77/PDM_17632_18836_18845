package com.example.myteamspage.Activities.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myteamspage.Activities.ComposeTweet
import com.example.myteamspage.Activities.LoginActivity
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityAccountOptionsBinding
import com.example.myteamspage.databinding.ActivityNotificationBinding
import com.example.myteamspage.databinding.ActivityPersonalInfoBinding

class Notification : AppCompatActivity() {
    private lateinit var binding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_teams -> startActivity(Intent(this@Notification, LoginActivity::class.java))
                R.id.bottom_nav_schedule -> startActivity(Intent(this@Notification, ComposeTweet::class.java))
                R.id.bottom_nav_publication -> startActivity(Intent(this@Notification, PublicationScreen::class.java))
                R.id.bottom_nav_settings -> startActivity(Intent(this@Notification, AccountOptions::class.java))
                else ->{
                }
            }
            true
        }
    }
}