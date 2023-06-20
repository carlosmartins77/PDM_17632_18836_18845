package com.example.myteamspage.Services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Classes.PublicationImage
import com.example.myteamspage.Classes.PublicationResponse
import com.example.myteamspage.Classes.Teams
import com.example.myteamspage.Classes.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myteamspage.Utils.SharedPreferencesFuncs


class GameServiceFunctions {
    val sharedPreferencesFuncs = SharedPreferencesFuncs()
    //val token = sharedPreferencesFuncs.loadData(context,"TOKEN_KEY").toString()

    fun createGameService(): GameService {
        val BASE_URL = "http://192.168.1.6:7060/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GameService::class.java)
    }

    fun scheduleGame(context: Context, token:String, idTeam1: String, idTeam2: String, location:String, gameDateTime: String, callback: (Boolean) -> Unit
    ) {
        val service = createGameService()

        val requestBody = mapOf(
            "idTeam1" to idTeam1,
            "idTeam2" to idTeam2,
            "location" to location,
            "gameDateTime" to gameDateTime
        )

        val call = service.scheduleGame(token, requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    Log.d("GAMESCHEDULEOK", response.toString())
                    callback(true)
                    Toast.makeText(context, "Game scheduled successfully", Toast.LENGTH_LONG).show()
                } else {
                    Log.d("GAMESCHEDULERROR", response.toString())
                    callback(false)
                    Toast.makeText(context, "Game scheduling failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Log.d("GAMESCHEDULEONFAILURE", t.message.toString())
                callback(false)
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun listAllUserGames(context: Context, token: String, callback: (List<Teams>) -> Unit) {
        val service = createGameService()
        val call = service.listAllUserGames(token)
        call.enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful) {
                    val gameResponse = response.body()?.message
                    Log.d("OkGetAll", gameResponse.toString())
                    if (gameResponse != null) {
                        callback(gameResponse)
                    }
                } else {
                    Log.d("ErrorGetAll", response.toString())
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETGAMES", t.message.toString())
            }
        })
    }
}