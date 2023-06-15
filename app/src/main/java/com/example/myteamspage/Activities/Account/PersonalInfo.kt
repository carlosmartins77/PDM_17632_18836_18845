package com.example.myteamspage.Activities.Account

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myteamspage.Activities.*
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityPersonalInfoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import java.util.*

class PersonalInfo : AppCompatActivity() {
    private lateinit var binding : ActivityPersonalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)
        binding = ActivityPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pickDate = findViewById<EditText>(R.id.birth_Date_field)
        val autoCompleteCountry: AutoCompleteTextView = findViewById(R.id.auto_complete_country)

        pickDate.setOnClickListener{
            showDatePicker(pickDate)
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.bottom_nav_settings
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
                R.id.bottom_nav_publication -> {
                    startActivity(Intent(applicationContext, PublicationScreen::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_settings -> true
                else -> false
            }
        }

        val sharedPreferences = getSharedPreferences(this.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val message = JSONObject(sharedPreferences.getString("CountryList","{}"))
        val countries = jsonObjectToList(message)

        val adapterDate = ArrayAdapter(this, R.layout.dropdown_list_item, countries)
        autoCompleteCountry.setAdapter(adapterDate)

        autoCompleteCountry.onItemClickListener = AdapterView.OnItemClickListener { AdapterView, view, i, l ->
            val selectedItem = AdapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePicker(editTextDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            editTextDate.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    fun jsonObjectToList(jsonObject: JSONObject): List<String> {
        val stringList = mutableListOf<String>()

        // Iterate over the keys of the JSON object
        val keysIterator = jsonObject.keys()
        while (keysIterator.hasNext()) {
            val key = keysIterator.next()
            val value = jsonObject.getString(key)

            // Add the key to the list
            stringList.add(key)
        }

        return stringList
    }

}