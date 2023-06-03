package com.example.myteamspage.Activities.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myteamspage.Activities.ComposeTweet
import com.example.myteamspage.Activities.LoginActivity
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityGeneralBinding
import com.example.myteamspage.databinding.ActivitySecurityBinding

class Security : AppCompatActivity() {
    private lateinit var binding : ActivitySecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_teams -> startActivity(Intent(this@Security, LoginActivity::class.java))
                R.id.bottom_nav_schedule -> startActivity(Intent(this@Security, ComposeTweet::class.java))
                R.id.bottom_nav_publication -> startActivity(Intent(this@Security, PublicationScreen::class.java))
                R.id.bottom_nav_settings -> startActivity(Intent(this@Security, AccountOptions::class.java))
                else ->{
                }
            }
            true
        }
    }
}