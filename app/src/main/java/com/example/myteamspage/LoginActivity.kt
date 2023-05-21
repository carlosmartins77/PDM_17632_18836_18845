package com.example.myteamspage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val emailEt = findViewById<EditText>(R.id.emailEt)
        val passwordEt = findViewById<EditText>(R.id.passwordEt)
        val forgotPasswordTV = findViewById<TextView>(R.id.forgotPasswordTV)

        //region forgot password

        forgotPasswordTV.setOnClickListener{
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        //endregion

        //region Login
        loginBtn.setOnClickListener {
            val emailTemp = emailEt.text.toString()
            val passwordTemp = passwordEt.text.toString()


            if(passwordTemp.length < 6) {
                Toast.makeText(applicationContext,"PASSWORD TOO SHORT!",Toast.LENGTH_LONG).show()
            }
            else  {

                //region arranjar validçao email
                //if(emailTemp.matches(Regex("/^\\S+@\\S+\\.\\S+\$/")))
                //{
                //    Log.d("Teste","teste")
               // }
                //endregion

                val BASE_URL = "http://192.168.1.254:3000/"

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(UserService::class.java)

                val requestBody = mapOf(
                    "email" to emailTemp,
                    "password" to passwordTemp
                )
                val call = service.loginUser(requestBody)

                call.enqueue(object : Callback<Map<String, String>> {
                    override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                        if (response.isSuccessful) {
                            //val token = response.body()?.get("token")

                            //region Start Schedule Game Activity
                            val intent = Intent(this@LoginActivity, ScheduleGame::class.java)
                            startActivity(intent)
                            //endregion

                            Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_LONG).show()
                        } else {
                            Log.d("responserequest", response.toString())
                            Toast.makeText(applicationContext, "Wrong email or password", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(applicationContext, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })




            }

            println("XD")

        }
        //endregion
    }
}