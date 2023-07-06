package com.example.myteamspage.Activities.Account

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myteamspage.Activities.*
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityPersonalInfoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import java.util.*
import com.example.myteamspage.Services.UserServiceFunctions
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.Utils.ConvertDatetimeFormat

class PersonalInfo : AppCompatActivity() {
    //private lateinit var binding : ActivityPersonalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        val sharedPreferencesFuncs = SharedPreferencesFuncs()
        val userServiceFunctions = UserServiceFunctions()
        val convertDatetimeFormat = ConvertDatetimeFormat()
        Log.d("TOKENTOKENTOKENTOKENTOKETNOEKTNEKO", sharedPreferencesFuncs.loadData(this, "TOKEN_KEY").toString())

        userServiceFunctions.getEmailByToken(this, sharedPreferencesFuncs.loadData(this, "TOKEN_KEY").toString()) {email, fullName ->
            userServiceFunctions.getUserInformation(this, email) { fullName, email, phoneNumber, birthDate, country ->
                findViewById<TextView>(R.id.personalInfoFullNameTv_value).text = fullName
                findViewById<TextView>(R.id.personalInfoEmailTv_value).text = email
                findViewById<TextView>(R.id.personalInfoPhoneNumberTv_value).text = phoneNumber
                findViewById<TextView>(R.id.personalInfoBirthDateTv_value).text = convertDatetimeFormat.convertMongoDBDateFormat(birthDate)
                findViewById<TextView>(R.id.personalInfoCountryTv_value).text = country
            }
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

}