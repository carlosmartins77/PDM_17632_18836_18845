package com.example.myteamspage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec
import java.util.*

class ScheduleGame : AppCompatActivity() {
    private var date: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game)

        val schedule_data = findViewById<CalendarView>(R.id.Calendar)
        val btn_schedule_game = findViewById<Button>(R.id.btnScheduleGame)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        recyclerView.adapter = MyAdapterRec(listOf("A","B","C","D","E","F","G","H","I","J"))
        recyclerView.layoutManager = LinearLayoutManager(this)


        schedule_data.setOnDateChangeListener { view, year, month, dayOfMonth ->
            date = "$dayOfMonth/$month/$year"
            Log.d("Date", date.toString())
        }

        btn_schedule_game.setOnClickListener {
            Toast.makeText(applicationContext, "Selected date: $date", Toast.LENGTH_SHORT).show()
        }


    }
}