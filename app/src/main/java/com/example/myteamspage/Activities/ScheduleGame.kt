package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityScheduleGameBinding


class ScheduleGame : AppCompatActivity() {
    private var date: String? = null
    private lateinit var binding : ActivityScheduleGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game)

        val schedule_data = findViewById<CalendarView>(R.id.Calendar)
        val btn_schedule_game = findViewById<Button>(R.id.btnScheduleGame)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)

        recyclerView.adapter = MyAdapterRec(listOf("A","B","C","D","E","F","G","H","I","J"))
        recyclerView.layoutManager = LinearLayoutManager(this)


        binding = ActivityScheduleGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_teams -> startActivity(Intent(this@ScheduleGame, LoginActivity::class.java))
                R.id.bottom_nav_schedule -> startActivity(Intent(this@ScheduleGame, ComposeTweet::class.java))
                R.id.bottom_nav_publication -> startActivity(Intent(this@ScheduleGame, PublicationScreen::class.java))
                R.id.bottom_nav_settings -> startActivity(Intent(this@ScheduleGame, LoginActivity::class.java))
                else ->{
                }
            }
            true
        }


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

    }
}