package com.example.myteamspage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val emailEt = findViewById<EditText>(R.id.emailET)
        val codeEt = findViewById<EditText>(R.id.codeET)
        val passwordEt = findViewById<EditText>(R.id.passwordET)
        val confirmPwdEt = findViewById<EditText>(R.id.confirmPasswordET)
        val resetPwdBtn = findViewById<Button>(R.id.resetPasswodBtn)

        resetPwdBtn.setOnClickListener{
            val email = emailEt.text.toString()
            val code = codeEt.text.toString()
            val password = passwordEt.text.toString()
            val confirmPwd = confirmPwdEt.text.toString()

            // if email is not valid email
            // aplicar regex

            if(password != confirmPwd)
            {
                Toast.makeText(applicationContext, "Passwords don't match!", Toast.LENGTH_LONG).show()
            }
            else if(password.length < 6)
            {
                Toast.makeText(applicationContext, "Password must be bigger than 6 characters!", Toast.LENGTH_LONG).show()
            }
            else {
                val BASE_URL = "http://192.168.1.254:3000/"

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(UserService::class.java)

                val requestBody = mapOf(
                    "email" to email,
                    "newpassword" to password,
                    "code" to code
                )
                val call = service.forgotPasswordUser(requestBody)

                call.enqueue(object : Callback<Map<String, String>> {
                    override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                        if (response.isSuccessful) {
                            //val token = response.body()?.get("token")

                            //region Go back to login page
                            val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
                            startActivity(intent)
                            //endregion

                            Toast.makeText(applicationContext, "Password changed successfully", Toast.LENGTH_LONG).show()
                        } else {
                            Log.d("responserequest", response.toString())
                            Toast.makeText(applicationContext, "Error updating password", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(applicationContext, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })

            }

            }

        }

    }
