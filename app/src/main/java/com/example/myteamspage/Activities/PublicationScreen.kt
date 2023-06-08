package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Adapters.PublicationAdapter
import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityPublicationScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class PublicationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPublicationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.publication_screen_recyclerview)
        val progressBar = findViewById<ProgressBar>(R.id.publication_progress_bar)

        val my_publication = findViewById<TextView>(R.id.publication_my_publication)
        val featherPen = findViewById<ImageView>(R.id.publication_btn_feather)

        // Put the progress value you want
        progressBar.max = 100
        progressBar.progress = 50

        // Create a list of Publication objects
        val publicationList: List<Publication> = listOf(
            Publication(R.drawable.baseline_image_24, "User 1", "Tweet 1", R.drawable.baseline_gif_box_24, R.drawable.arrow_drop_down),
            Publication(R.drawable.arrow_drop_down, "User 2", "Tweet 2", R.drawable.baseline_image_24, R.drawable.baseline_gif_box_24),
            Publication(R.drawable.baseline_gif_box_24, "User 3", "Tweet 3", R.drawable.arrow_drop_down, R.drawable.baseline_image_24)
        )

        val publicationAdapter = PublicationAdapter(publicationList)
        recyclerView.adapter = publicationAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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