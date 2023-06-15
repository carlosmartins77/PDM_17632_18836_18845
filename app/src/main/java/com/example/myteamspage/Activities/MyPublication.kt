package com.example.myteamspage.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.PersonalInfo
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Adapters.PublicationAdapter
import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.Classes.SQLitePublication
import com.example.myteamspage.R
import com.example.myteamspage.Services.PublicationServiceFunction
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.databinding.ActivityMyPublicationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


fun listpubsbyuser(context: Context): List<Publication> {
    val databaseHelper = SQLitePublication(context)
    val db = databaseHelper.readableDatabase

    val columns = arrayOf("COLUMN_USERNAME", "COLUMN_CONTENT", "COLUMN_DATE")
    val cursor = db.query("TABLE_NAME", columns, null, null, null, null, null) // publications

    val publications = mutableListOf<Publication>()

    val columnIndexUsername = cursor.getColumnIndex("COLUMN_USERNAME")
    val columnIndexContent = cursor.getColumnIndex("COLUMN_CONTENT")
    val columnIndexDate = cursor.getColumnIndex("COLUMN_DATE")

    while (cursor.moveToNext()) {
        val username = if (columnIndexUsername != -1) cursor.getString(columnIndexUsername) else ""
        val content = if (columnIndexContent != -1) cursor.getString(columnIndexContent) else ""
        val date = if (columnIndexDate != -1) cursor.getString(columnIndexDate) else ""

        val publication = Publication(
            R.drawable.baseline_image_24,
            username,
            content,
            R.drawable.baseline_gif_box_24,
            R.drawable.arrow_drop_down
        )
        publications.add(publication)
        Log.d("LOGCATPUBLICATIONSQL", publications.toString())
    }

    cursor.close()
    db.close()

    return publications
}


class MyPublication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_publication)

        val binding = ActivityMyPublicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val publicationServiceFunctions = PublicationServiceFunction()
        val sharedPreferencesFuncs = SharedPreferencesFuncs()

        val token = sharedPreferencesFuncs.loadData(this,"TOKEN_KEY").toString()

        val recyclerView = findViewById<RecyclerView>(R.id.my_publication_recyclerview)

        val feed = findViewById<TextView>(R.id.my_publication_feed)
        val featherPen = findViewById<ImageView>(R.id.my_publication_btn_feather)

        ////
        val publicationList = listpubsbyuser(this) // Retrieve data from SQLite database
        val adapter = PublicationAdapter(publicationList) // Create adapter with the data
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        ////

        /*
        // Create a list of Publication objects
        val publicationList: List<Publication> = listOf(
            Publication(R.drawable.baseline_image_24, "User 1", "Tweet 1", R.drawable.baseline_gif_box_24, R.drawable.arrow_drop_down),
            Publication(R.drawable.arrow_drop_down, "User 2", "Tweet 2", R.drawable.baseline_image_24, R.drawable.baseline_gif_box_24),
            Publication(R.drawable.baseline_gif_box_24, "User 3", "Tweet 3", R.drawable.arrow_drop_down, R.drawable.baseline_image_24)
        )

        val publicationAdapter = PublicationAdapter(publicationList)

        recyclerView.adapter = publicationAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

*/


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.my_publication_bottomNavigationView)
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
                    startActivity(Intent(applicationContext, PersonalInfo::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        featherPen.setOnClickListener {
            val intent = Intent(this@MyPublication, ComposeTweet::class.java)
            startActivity(intent)
            finish()
        }

        feed.setOnClickListener {
            val intent = Intent(this@MyPublication, PublicationScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}
