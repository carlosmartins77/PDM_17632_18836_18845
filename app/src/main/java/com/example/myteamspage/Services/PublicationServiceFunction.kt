package com.example.myteamspage.Services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Activities.Logo_KickOff
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.UserService
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PublicationServiceFunction {

    val sharedPreferencesFuncs = SharedPreferencesFuncs()

    private fun createPublicationService(): PublicationService {
        val BASE_URL = "http://192.168.1.68:7070/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PublicationService::class.java)
    }

    fun postPublication(context: Context, token: String, username: String, content: String) {
        val service = createPublicationService()

        val requestBody = mapOf(
            "username" to username,
            "content" to content,
        )

        val call = service.postPublication(token, requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, PublicationScreen::class.java)
                    context.startActivity(intent)
                    Toast.makeText(context, "Publication created successful", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Log.d("ERROPUB:", response.toString())
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun listpubsbyuser(context: Context, token: String, username: String, content: String) {
        val service = createPublicationService()
        val call = service.listpubsbyuser(token)
        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val pubResponse = response.body()
                    val pub = pubResponse?.get("message") ?: emptyList()
                    //callback(pub.sorted())
                } else {
                    Log.d("ErrorPublicationGetByUser", response.toString())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETPUBLICATION", t.message.toString())
            }
        })
    }

    fun listallpub(context: Context, token: String, username: String, content: String) {
        val service = createPublicationService()
        val call = service.listallpub(token)
        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val pubResponse = response.body()
                    val pub = pubResponse?.get("message") ?: emptyList()
                    //callback(teams.sorted())
                } else {
                    Log.d("ErrorPublicationGetByUser", response.toString())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETPUBLICATION", t.message.toString())

            }
        })
    }

}