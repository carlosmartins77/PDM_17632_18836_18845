package com.example.myteamspage.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myteamspage.Activities.CreateAccount.Create_your_team_register
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions
import com.example.myteamspage.Utils.InternetConnectivity

class Logo_KickOff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_kick_off)

        val getStarted = findViewById<Button>(R.id.logo_kick_off_button1)
        val alreadyHaveAccount = findViewById<Button>(R.id.logo_kick_off_button2)
        val userServiceFunctions = UserServiceFunctions()
        val sharedPreferences = getSharedPreferences(this.resources.getString(R.string.app_name), Context.MODE_PRIVATE)


        getStarted.setOnClickListener{
            val intent = Intent(this@Logo_KickOff, Create_your_team_register::class.java)
            startActivity(intent)
        }

        alreadyHaveAccount.setOnClickListener{
            val intent = Intent(this@Logo_KickOff, LoginActivity::class.java)
            startActivity(intent)
        }

        userServiceFunctions.getAllCountries { countries ->
            sharedPreferences.edit().putString("CountryList",countries.toString()).commit()
        }
    }

    override fun onStart(){
        super.onStart()
        val internetConnectivity = InternetConnectivity()

        if (internetConnectivity.isInternetConnected(this) == false) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

}