package com.example.myteamspage.Activities.TeamsHome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Adapters.Teams.TeamsAdapter
import com.example.myteamspage.R
import com.example.myteamspage.Services.GameServiceFunctions
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.databinding.ActivityTeamsHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeamsHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_home_page)

            val binding = ActivityTeamsHomePageBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val sharedPreferencesFuncs = SharedPreferencesFuncs()
            val gameServiceFunctions = GameServiceFunctions()
            val token = sharedPreferencesFuncs.loadData(this, "TOKEN_KEY").toString()

            binding.rvListTeamsCaptain.layoutManager = LinearLayoutManager(this)

            gameServiceFunctions.listAllUserGames(this, token) { games ->
                val adapter = TeamsAdapter(games)
                binding.rvListTeamsCaptain.adapter = adapter
            }

            val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.selectedItemId = R.id.bottom_nav_teams
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_nav_teams -> true
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
                    R.id.bottom_nav_settings -> {
                        startActivity(Intent(applicationContext, AccountOptions::class.java))
                        finish()
                        true
                    }
                    else -> false
                }
            }
    }
}