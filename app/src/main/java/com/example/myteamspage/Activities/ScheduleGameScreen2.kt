package com.example.myteamspage.Activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.R
import java.util.*

class ScheduleGameScreen2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game_screen2)

        val date = arrayOf("12/02/2023", "30/03/2023", "01/05/2023", "02/06/2023", "18/08/2023")
        val team = arrayOf("Real", "Arsenal", "Benfica", "Canelas", "Braga")
        val oponent = arrayOf("Porto", "Famalicao", "M. City", "Inter", "Napoles")
        val location = arrayOf("Braga", "Porto", "New York", "Berlim", "Vila Real")

        val pickDate = findViewById<TextView>(R.id.schedule_game2_date_field)
        val autoCompleteTeam: AutoCompleteTextView = findViewById(R.id.auto_complete_team)
        val autoCompleteOponent: AutoCompleteTextView = findViewById(R.id.auto_complete_oponent)
        val autoCompleteLocation: AutoCompleteTextView = findViewById(R.id.auto_complete_location)

        val schedule_date = intent.getStringExtra("calendar_date")

        pickDate.setText(schedule_date)

        val adapterTeam = ArrayAdapter(this, R.layout.dropdown_list_item, team)
        autoCompleteTeam.setAdapter(adapterTeam)

        autoCompleteTeam.onItemClickListener = AdapterView.OnItemClickListener{ AdapterView, view, i, l ->
            val selectedItem = AdapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        val adapterOponent = ArrayAdapter(this, R.layout.dropdown_list_item, oponent)
        autoCompleteOponent.setAdapter(adapterOponent)

        autoCompleteOponent.onItemClickListener = AdapterView.OnItemClickListener{ AdapterView, view, i, l ->
            val selectedItem = AdapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        val adapterLocation = ArrayAdapter(this, R.layout.dropdown_list_item, location)
        autoCompleteLocation.setAdapter(adapterLocation)

        autoCompleteLocation.onItemClickListener = AdapterView.OnItemClickListener{ AdapterView, view, i, l ->
            val selectedItem = AdapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        val arrowBack = findViewById<ImageView>(R.id.schedule_game_screen2_arrow_icon)
        val btn_cancel = findViewById<Button>(R.id.schedule_game2_btn_cancel)
        val btn_finish = findViewById<Button>(R.id.schedule_game2_btn_finish)

        // val schedule_data = findViewById<TextView>(R.id.SELECTDATEFIELD)
        //val calendar_date = intent.getStringExtra("calendar_date")

        //schedule_data.text = calendar_date


        // myIcon.setOnClickListener {
        //   Log.d("Entrou", calendar_date.toString())
        //   myRecyclerView.visibility = View.VISIBLE
        //}

        pickDate.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                pickDate.setText(formattedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        arrowBack.setOnClickListener {
            val intent = Intent(this@ScheduleGameScreen2, ScheduleGame::class.java)
            startActivity(intent)
            finish()
        }

        btn_finish.setOnClickListener {
            val intent = Intent(this@ScheduleGameScreen2, ScheduleGame::class.java)
            startActivity(intent)
            finish()
        }

        btn_cancel.setOnClickListener {
            val intent = Intent(this@ScheduleGameScreen2, ScheduleGame::class.java)
            startActivity(intent)
            finish()
        }
    }
}

