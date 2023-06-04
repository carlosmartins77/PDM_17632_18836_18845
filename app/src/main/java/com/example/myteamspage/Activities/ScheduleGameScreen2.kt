package com.example.myteamspage.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.R

class ScheduleGameScreen2 : AppCompatActivity() {

    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myIcon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game_screen2)

        val schedule_data = findViewById<TextView>(R.id.SELECTDATEFIELD)
        val calendar_date = intent.getStringExtra("calendar_date")

        schedule_data.text = calendar_date

        myIcon = findViewById(R.id.SELECTTEAMFIELD)

        myIcon.setOnClickListener {
            Log.d("Entrou", calendar_date.toString())
            myRecyclerView.visibility = View.VISIBLE
        }

    }
}
