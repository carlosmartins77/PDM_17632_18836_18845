package com.example.myteamspage.Activities.Account

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myteamspage.Activities.CreateAccount.SetNotifications
import com.example.myteamspage.Activities.Logo_KickOff
import com.example.myteamspage.Activities.MyPublication
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Activities.TeamsHome.TeamsHomePage
import com.example.myteamspage.Adapters.Account.AdapterRec
import com.example.myteamspage.Classes.Account
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.databinding.ActivityAccountOptionsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class AccountOptions : AppCompatActivity() {
    companion object {
        const val CUSTOM_ACTION = "com.example.broadcast.CUSTOM_ACTION"
        const val EXTRA_MESSAGE = "com.example.broadcast.EXTRA_MESSAGE"
    }
    val sharedPreferencesFuncs = SharedPreferencesFuncs()

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

        val userServiceFunctions = UserServiceFunctions()
        userServiceFunctions.getEmailByToken(this, sharedPreferencesFuncs.loadData(this,"TOKEN_KEY").toString()) { email, fullName ->
            findViewById<TextView>(R.id.usernameLabel).text = fullName
            findViewById<TextView>(R.id.userEmailLabel).text = email
        }

        val binding = ActivityAccountOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.bottom_nav_settings

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_teams -> {
                    startActivity(Intent(applicationContext, TeamsHomePage::class.java))
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


    fun addDataToRecycleView(){
        val accountOptions: List<Account> = listOf(
            Account(R.drawable.personalinfo, "Personal Info"),
            Account(R.drawable.notification, "Notifications"),
            Account(R.drawable.friendlist, "Friend List"),
            Account(R.drawable.general, "General"),
            Account(R.drawable.security, "Security"),
            Account(R.drawable.helpcenter, "Help Center"),
            Account(R.drawable.aboutkickoff, "About KickOff"),
            Account(R.drawable.logout, "Logout")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.menuOptionsRecyclerView)
        recyclerView.adapter = AdapterRec(accountOptions)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showCustomDialogBox(){
        val dialogBinding = layoutInflater.inflate(R.layout.log_out_layout, null)
        val dialog = Dialog(this)
        val cancelBtn = dialogBinding.findViewById<Button>(R.id.pwdCancelBtn)
        val YesBtn = dialogBinding.findViewById<Button>(R.id.pwdSuccessfullyBtn)


        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        YesBtn.setOnClickListener{
            val settings: SharedPreferences = this.getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            settings.edit().clear().commit()
            val intent = Intent(this, Logo_KickOff::class.java)
            startActivity(intent)
        }

        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}