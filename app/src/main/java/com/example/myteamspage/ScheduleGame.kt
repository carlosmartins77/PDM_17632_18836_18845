package com.example.myteamspage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec

class ScheduleGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game)

        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        recyclerView.adapter = MyAdapterRec(listOf("A","B","C","D","E","F","G","H","I","J"))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}