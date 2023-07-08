package com.example.myteamspage.Activities.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myteamspage.Activities.*
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Activities.TeamsHome.TeamsHomePage
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityNotificationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Notification : AppCompatActivity() {
    private lateinit var binding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.bottom_nav_settings

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_teams -> {
                    startActivity(Intent(applicationContext, TeamsHomePage::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_schedule -> {
                    startActivity(Intent(applicationContext, ScheduleGame::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_publication -> {
                    startActivity(Intent(applicationContext, PublicationScreen::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_settings -> true
                else -> false
            }
        }
    }
}