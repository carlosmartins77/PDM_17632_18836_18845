package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myteamspage.Activities.Account.PersonalInfo
import com.example.myteamspage.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.myteamspage.Services.PublicationServiceFunction
import com.example.myteamspage.Utils.SharedPreferencesFuncs

class ComposeTweet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_tweet)

        val pubServiceFunctions = PublicationServiceFunction()
        val sharedPreferencesFuncs = SharedPreferencesFuncs()
        val email = sharedPreferencesFuncs.loadData(this,"USERNAME_KEY").toString()
        val token = sharedPreferencesFuncs.loadData(this,"TOKEN_KEY").toString()
        val editText = findViewById<EditText>(R.id.editProfile)

        val arrowBack = findViewById<ImageView>(R.id.compose_tweet_arrow_icon)
        val btn_publish = findViewById<Button>(R.id.compose_pub_btn_publish)

        arrowBack.setOnClickListener {
            val intent = Intent(this@ComposeTweet, PublicationScreen::class.java)
            startActivity(intent)
            finish()
        }

        btn_publish.setOnClickListener {
            Log.d("ComposeTwett", email.toString())
            val inputText = editText.text.toString()
            Log.d("ComposeTwett", inputText.toString())
            pubServiceFunctions.postPublication(this, token, email , inputText)
        }
    }
}