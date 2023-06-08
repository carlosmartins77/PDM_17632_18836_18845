package com.example.myteamspage.Activities.Account

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.*
import com.example.myteamspage.Adapters.Account.AdapterRec
import com.example.myteamspage.R
import com.example.myteamspage.databinding.ActivityAccountOptionsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountOptions : AppCompatActivity() {
    companion object {
        const val CUSTOM_ACTION = "com.example.broadcast.CUSTOM_ACTION"
        const val EXTRA_MESSAGE = "com.example.broadcast.EXTRA_MESSAGE"
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //val message = intent?.getStringExtra(EXTRA_MESSAGE)
            //Toast.makeText(context, "Received message: $message", Toast.LENGTH_SHORT).show()
            showCustomDialogBox()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_options)

        val binding = ActivityAccountOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    startActivity(Intent(applicationContext, MyPublication::class.java))
                    finish()
                    true
                }
                R.id.bottom_nav_settings -> true
                else -> false
            }
        }

        addUserInformation()
        addDataToRecycleView()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(CUSTOM_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
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

    private fun showCustomDialogBox(){
        val dialogBinding = layoutInflater.inflate(R.layout.log_out_layout, null)
        val dialog = Dialog(this)

        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}