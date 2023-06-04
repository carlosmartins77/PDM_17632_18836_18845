package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Adapters.PublicationAdapter
import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityPublicationScreenBinding

class PublicationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPublicationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.publication_screen_recyclerview)
        val progressBar = findViewById<ProgressBar>(R.id.publication_progress_bar)

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

    }
}