package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions

class ConfirmCode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_code)

        val userServiceFunctions = UserServiceFunctions()
        val email = intent.getStringExtra("forgotPwdEmail")
        val continueBtn = findViewById<Button>(R.id.continueAddCodeBtn)
        val confirmCodeEt = findViewById<EditText>(R.id.codeEmailNumber)
        Log.d("emailpwdforgot", email.toString())
        Log.d("confirmcodepwdforgot", confirmCodeEt.text.toString())

        continueBtn.setOnClickListener {
           var confirmCode = confirmCodeEt.text.toString()
           userServiceFunctions.verifyResetCode(this, email.toString(), confirmCode)
        }
    }
}