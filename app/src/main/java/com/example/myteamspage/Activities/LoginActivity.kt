package com.example.myteamspage.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myteamspage.Activities.ForgotPassword.ForgotPWD
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions
import com.example.myteamspage.UserService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myteamspage.Utils.SharedPreferencesFuncs

class LoginActivity : AppCompatActivity() {

    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        passwordEditText = findViewById(R.id.passwordText)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val emailEt = findViewById<EditText>(R.id.emailEt)
        val forgotPasswordTV = findViewById<TextView>(R.id.forgotPasswordTV)
        val userServiceFunctions = UserServiceFunctions()
        val sharedPreferencesFuncs = SharedPreferencesFuncs()
        //region forgot password

        forgotPasswordTV.setOnClickListener{
            //val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            val intent = Intent(this@LoginActivity, ForgotPWD::class.java)
            startActivity(intent)
        }

        //endregion

        //region Login
        loginBtn.setOnClickListener {

            //val intent = Intent(this@LoginActivity, ScheduleGame::class.java)
            //startActivity(intent)

            val email = emailEt.text.toString()
            val password = passwordEditText.text.toString()

            if(password.length < 6) {
                Toast.makeText(applicationContext,"PASSWORD TOO SHORT!",Toast.LENGTH_LONG).show()
            }
            else {
                userServiceFunctions.login(this, email, password)
            }
            }
        }
        //endregion
    }
