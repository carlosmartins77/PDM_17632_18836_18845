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

class PersonalInfo : AppCompatActivity() {
    //private lateinit var binding : ActivityPersonalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        val sharedPreferencesFuncs = SharedPreferencesFuncs()
        val userServiceFunctions = UserServiceFunctions()

        val emailTkn = sharedPreferencesFuncs.loadData(this, "USER_EMAIL_BY_TKN").toString()
        userServiceFunctions.getUserInformation(this, emailTkn)

        val fullName = sharedPreferencesFuncs.loadData(this,  "USER_FULLNAME").toString()
        val email = sharedPreferencesFuncs.loadData(this,  "USER_EMAIL").toString()
        val phoneNumber = sharedPreferencesFuncs.loadData(this,  "USER_PHONENUMBER").toString()
        val birthDate = sharedPreferencesFuncs.loadData(this,  "USER_BIRTHDATE").toString()
        val country = sharedPreferencesFuncs.loadData(this,  "USER_COUNTRY").toString()

        val fullNameTv = findViewById<TextView>(R.id.personalInfoFullNameTv_value).setText(fullName)
        val emailTv = findViewById<TextView>(R.id.personalInfoEmailTv_value).setText(email)
        val phoneNumberTv = findViewById<TextView>(R.id.personalInfoPhoneNumberTv_value).setText(phoneNumber)
        val birthDateTv = findViewById<TextView>(R.id.personalInfoBirthDateTv_value).setText(birthDate)
        val countryTv = findViewById<TextView>(R.id.personalInfoCountryTv_value).setText(country)

        Log.d("fullNameluserinfo", fullName)
        Log.d("emailuserinfo", email)
        Log.d("phoneNumberuserinfo", phoneNumber)
        Log.d("birthDateuserinfo", birthDate)
        Log.d("countryuserinfo", country)


        //binding = ActivityPersonalInfoBinding.inflate(layoutInflater)
        //setContentView(binding.root)



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


        /*
        val sharedPreferences = getSharedPreferences(this.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val message = JSONObject(sharedPreferences.getString("CountryList","{}"))
        val countries = jsonObjectToList(message)

        val adapterDate = ArrayAdapter(this, R.layout.dropdown_list_item, countries)
        autoCompleteCountry.setAdapter(adapterDate)

        autoCompleteCountry.onItemClickListener = AdapterView.OnItemClickListener { AdapterView, view, i, l ->
            val selectedItem = AdapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }
        */
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