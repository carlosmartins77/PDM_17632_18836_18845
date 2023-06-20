package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Adapters.Publications.PublicationFeedAdapter
import com.example.myteamspage.Adapters.Teams.TeamsAdapter
import com.example.myteamspage.R
import com.example.myteamspage.Services.GameServiceFunctions
import com.example.myteamspage.Services.TeamServiceFunctions
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.databinding.ActivityTeamsPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeamsHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_home_page)

        try {

            val binding = ActivityTeamsPageBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val sharedPreferencesFuncs = SharedPreferencesFuncs()
            val gameServiceFunctions = GameServiceFunctions()
            val token = sharedPreferencesFuncs.loadData(this, "TOKEN_KEY").toString()

            val recyclerView = findViewById<RecyclerView>(R.id.rvListTeamsCaptain)
            recyclerView.layoutManager = LinearLayoutManager(this)

            gameServiceFunctions.listAllUserGames(this, token) { games ->
                Log.d("TEAMSHOMEPAGE", "ENTROU")
                // val adapter = TeamsAdapter(games)
                // recyclerView.adapter = adapter
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

        }catch (e: NullPointerException) {
            // Handle the exception
           Log.d("An error occurred:", "${e.message}")
        }
    }
}