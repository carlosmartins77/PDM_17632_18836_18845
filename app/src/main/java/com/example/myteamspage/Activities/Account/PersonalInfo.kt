package com.example.myteamspage.Activities.Account

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.myteamspage.Activities.*
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityAccountOptionsBinding
import com.example.myteamspage.databinding.ActivityPersonalInfoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class PersonalInfo : AppCompatActivity() {
    private lateinit var binding : ActivityPersonalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        binding = ActivityPersonalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val birthDateEt = findViewById<EditText>(R.id.complete_your_profile_date_of_birth_field)
        val pickDateBtn = findViewById<Button>(R.id.complete_your_profile_pick_dateBtn)

        //Adicionar as opções à combo para escolher o pais
        val options = listOf("Alemanha", "Espanha", "Portugal", "Ucrania")
        val dropdownAutoCompleteTextView: AutoCompleteTextView = findViewById(R.id.dropdown)
        val dropdownLayoutAutoCompleteTextView: TextInputLayout = findViewById(R.id.dropdown_layout)

        val adapter = ArrayAdapter(this, R.layout.list_item, options)
        dropdownAutoCompleteTextView.setAdapter(adapter)

        dropdownLayoutAutoCompleteTextView.setEndIconOnClickListener {
            dropdownAutoCompleteTextView.setText("", false)
        }

        pickDateBtn.setOnClickListener{
            showDatePicker(birthDateEt)
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
}