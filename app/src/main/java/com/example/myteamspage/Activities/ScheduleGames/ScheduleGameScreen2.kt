package com.example.myteamspage.Activities.ScheduleGames

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myteamspage.R
import java.util.*
import com.example.myteamspage.Services.TeamServiceFunctions
import com.example.myteamspage.Services.GameServiceFunctions
import com.example.myteamspage.Utils.SharedPreferencesFuncs
class ScheduleGameScreen2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_game_screen2)

        val teamServiceFunctions = TeamServiceFunctions()
        val gameServiceFunctions = GameServiceFunctions()
        val sharedPreferencesFuncs = SharedPreferencesFuncs()

        val location = arrayOf("Braga", "OPorto", "New York", "Berlin", "Manchester")

        val pickDate = findViewById<TextView>(R.id.schedule_game2_date_field)
        val autoCompleteTeam: AutoCompleteTextView = findViewById(R.id.auto_complete_team)
        val autoCompleteOpponent: AutoCompleteTextView = findViewById(R.id.auto_complete_oponent)
        val autoCompleteLocation: AutoCompleteTextView = findViewById(R.id.auto_complete_location)
        val token = sharedPreferencesFuncs.loadData(this,"TOKEN_KEY").toString()

        val schedule_date = intent.getStringExtra("calendar_date")

        pickDate.setText(schedule_date)

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

        teamServiceFunctions.getCaptainTeams(token) { teams ->
            val adapterDate = ArrayAdapter(this, R.layout.dropdown_list_item, teams)
            autoCompleteTeam.setAdapter(adapterDate)

            autoCompleteTeam.onItemClickListener = AdapterView.OnItemClickListener { AdapterView, view, i, l ->
                val selectedItem = AdapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
            }
        }

        teamServiceFunctions.getAllTeams(token) { teams ->
            val adapterOponent = ArrayAdapter(this, R.layout.dropdown_list_item, teams)
            autoCompleteOpponent.setAdapter(adapterOponent)

            autoCompleteOpponent.onItemClickListener = AdapterView.OnItemClickListener { AdapterView, view, i, l ->
                val selectedItem = AdapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
            }
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

        btn_finish.setOnClickListener {
            gameServiceFunctions.scheduleGame(this,
                token,
                autoCompleteTeam.text.toString(),
                autoCompleteOpponent.text.toString(),
                autoCompleteLocation.text.toString(),
                pickDate.text.toString()
            ) { success ->
                if (success) {
                    val intent = Intent(this@ScheduleGameScreen2, GameSchedule::class.java)
                    val gameAddress = autoCompleteLocation.text.toString()
                    val team = autoCompleteTeam.text.toString()
                    val opponent = autoCompleteOpponent.text.toString()

                    intent.putExtra("gameAddress", gameAddress)
                    intent.putExtra("gameTeam", team)
                    intent.putExtra("gameOpponent", opponent)
                    intent.putExtra("gameDate", pickDate.text.toString())

                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Check the values specified for the game scheduling!", Toast.LENGTH_LONG).show()
                }
            }
        }


        // val schedule_data = findViewById<TextView>(R.id.SELECTDATEFIELD)
        //val calendar_date = intent.getStringExtra("calendar_date")

        //schedule_data.text = calendar_date


        // myIcon.setOnClickListener {
        //   Log.d("Entrou", calendar_date.toString())
        //   myRecyclerView.visibility = View.VISIBLE
        //}


        arrowBack.setOnClickListener {
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

