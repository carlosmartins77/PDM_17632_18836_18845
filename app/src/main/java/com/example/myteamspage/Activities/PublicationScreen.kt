package com.example.myteamspage.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.Adapters.PublicationAdapter
import com.example.myteamspage.Publication
import com.example.myteamspage.R

class PublicationScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication_screen)

        val recyclerView: RecyclerView = findViewById(R.id.publication_recyclerview)

        // Create a list of Publication objects
        val publicationList: List<Publication> = listOf(
            Publication(R.drawable.baseline_image_24, "User 1", "Tweet 1", R.drawable.baseline_gif_box_24, R.drawable.arrow_drop_down),
            Publication(R.drawable.arrow_drop_down, "User 2", "Tweet 2", R.drawable.baseline_image_24, R.drawable.baseline_gif_box_24),
            Publication(R.drawable.baseline_gif_box_24, "User 3", "Tweet 3", R.drawable.arrow_drop_down, R.drawable.baseline_image_24),
        )

        // Create an instance of the PublicationAdapter
        val adapter = PublicationAdapter(publicationList)

        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter

        // Set a LinearLayoutManager to the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}