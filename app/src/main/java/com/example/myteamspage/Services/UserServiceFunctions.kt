package com.example.myteamspage.Services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Activities.CompleteYourProfile
import com.example.myteamspage.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserServiceFunctions {

    fun signUp(context: Context, email: String, password: String, fullName: String, country: String, birthdate: String, phoneNumber: String)
    {
        val BASE_URL = "http://192.168.1.2:3000/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)

        val requestBody = mapOf(
            "email" to email,
            "password" to password,
            "fullname" to fullName,
            "country" to country,
            "birthdate" to birthdate,
            "phonenumber" to phoneNumber
        )

        val call = service.signUpUser(requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, CompleteYourProfile::class.java)
                    context.startActivity(intent)
                    Toast.makeText(context, "Register successful", Toast.LENGTH_LONG).show()
                } else {
                    Log.d("responsecodenestjs", response.toString())
                    Toast.makeText(context, "Register failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}