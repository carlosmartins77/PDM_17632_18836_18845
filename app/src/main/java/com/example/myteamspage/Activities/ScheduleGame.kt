package com.example.myteamspage.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.Account.AccountOptions
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.TeamsHome.TeamsScreen
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.Classes.Games
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityScheduleGameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ScheduleGame : AppCompatActivity() {
    private var date: String? = null
    private lateinit var binding : ActivityScheduleGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game)

        val binding = ActivityScheduleGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val schedule_data = findViewById<CalendarView>(R.id.Calendar)
        val btn_schedule_game = findViewById<Button>(R.id.btnScheduleGame)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)

        // Create a list of Games objects
        val gamesList: List<Games> = listOf(
            Games(R.drawable.realmadrid, "12/01/2023 | 12:20", R.drawable.arsenal),
            Games(R.drawable.internazionalemilano, "30/03/2023 | 21:40", R.drawable.manchestercity),
            Games(R.drawable.atleticodemadrid, "1/04/2023 | 19:00", R.drawable.barcelona),
        )

        val gamesAdapter = MyAdapterRec(gamesList)
        recyclerView.adapter = gamesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        schedule_data.setOnDateChangeListener { view, year, month, dayOfMonth ->
            date = "$dayOfMonth/$month/$year"
            Log.d("Date", date.toString())
        }

        btn_schedule_game.setOnClickListener {
            Toast.makeText(applicationContext, "Selected date: $date", Toast.LENGTH_SHORT).show()
            // Start Schedule Game Screen 2 Activity
            val intent = Intent(this@ScheduleGame, ScheduleGameScreen2::class.java)
            intent.putExtra("calendar_date", date);
            startActivity(intent)
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.bottom_nav_schedule
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_teams ->{
                    startActivity(Intent(applicationContext, TeamsScreen::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_schedule ->  true
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