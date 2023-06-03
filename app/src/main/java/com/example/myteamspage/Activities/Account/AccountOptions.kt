package com.example.myteamspage.Activities.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.ComposeTweet
import com.example.myteamspage.Activities.LoginActivity
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.Adapters.Account.AdapterRec
import com.example.myteamspage.Adapters.MyAdapterRec
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityAccountOptionsBinding

class AccountOptions : AppCompatActivity() {
    private lateinit var binding : ActivityAccountOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_options)

        binding = ActivityAccountOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_teams -> startActivity(Intent(this@AccountOptions, LoginActivity::class.java))
                R.id.bottom_nav_schedule -> startActivity(Intent(this@AccountOptions, ComposeTweet::class.java))
                R.id.bottom_nav_publication -> startActivity(Intent(this@AccountOptions, PublicationScreen::class.java))
                R.id.bottom_nav_settings -> startActivity(Intent(this@AccountOptions, AccountOptions::class.java))
                else ->{
                }
            }
            true
        }

        addUserInformation()
        addDataToRecycleView()
    }

    fun addUserInformation(){
        val userName = findViewById<TextView>(R.id.usernameLabel)
        val userEmail = findViewById<TextView>(R.id.userEmailLabel)
        userName.text = "Ricardo Azevedo"
        userEmail.text = "ricardo@gmail.com"
    }

    fun addDataToRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.menuOptionsRecyclerView)
        recyclerView.adapter = AdapterRec(listOf("Personal Info","Notifications","Friend List","General","Security","Help Center","About KickOff","Logout"))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}