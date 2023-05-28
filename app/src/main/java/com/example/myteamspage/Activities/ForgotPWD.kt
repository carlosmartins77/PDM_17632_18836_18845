package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions

class ForgotPWD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pwd)

        val userServiceFunctions = UserServiceFunctions()
        val continueBtn = findViewById<Button>(R.id.continueForgotPWDBtn)
        val emailEt = findViewById<EditText>(R.id.forgotPwdEmailEt)

        continueBtn.setOnClickListener {
            Log.d("emailforgotpwd", emailEt.text.toString())
            userServiceFunctions.sendRecoveryCodeUser(this, emailEt.text.toString())
        }

    }
}