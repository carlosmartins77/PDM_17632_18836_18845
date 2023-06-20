package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Adapters.Publications.PublicationFeedAdapter
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityPublicationScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.myteamspage.Services.PublicationServiceFunction
import com.example.myteamspage.Utils.SharedPreferencesFuncs

class PublicationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val publicationServiceFunction = PublicationServiceFunction()
        val sharedPreferencesFuncs = SharedPreferencesFuncs()
        val token = sharedPreferencesFuncs.loadData(this, "TOKEN_KEY").toString()

        val binding = ActivityPublicationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.publication_screen_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        publicationServiceFunction.listAllPubs(this, token) { publicationsList ->
            val adapter = PublicationFeedAdapter(publicationsList)
            recyclerView.adapter = adapter
        }

        val my_publication = findViewById<TextView>(R.id.publication_my_publication)
        val featherPen = findViewById<ImageView>(R.id.publication_btn_feather)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.bottom_nav_publication
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_teams -> {
                    startActivity(Intent(applicationContext, SetNotifications::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_schedule -> {
                    startActivity(Intent(applicationContext, ScheduleGame::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_publication -> true
                R.id.bottom_nav_settings -> {
                    startActivity(Intent(applicationContext, AccountOptions::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        my_publication.setOnClickListener {
            val intent = Intent(this@PublicationScreen, MyPublication::class.java)
            startActivity(intent)
        }

        featherPen.setOnClickListener {
            val intent = Intent(this@PublicationScreen, ComposeTweet::class.java)
            startActivity(intent)
        }
    }
}