package com.example.myteamspage.Services
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamServiceFunctions {

    fun createTeamService(): TeamService {
        val BASE_URL = "http://192.168.1.68:7080/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TeamService::class.java)
    }

    fun getAllTeams(token: String, callback: (List<String>) -> Unit) {
        val service = createTeamService()
        val call = service.getAllTeams(token)

        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val teamsResponse = response.body()
                    val teams = teamsResponse?.get("message") ?: emptyList()
                    callback(teams.sorted())
                } else {
                    Log.d("ErrorTeamsGetAllTeams", response.toString())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETTEAM", t.message.toString())

            }
        })
    }


    fun getOpponentTeams(token: String, callback: (List<String>) -> Unit) {
        val service = createTeamService()
        val call = service.getOpponentTeams(token)

        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val teamsResponse = response.body()
                    val teams = teamsResponse?.get("message") ?: emptyList()
                    callback(teams.sorted())
                } else {
                    Log.d("ErrorTeamsGetAllTeams", response.toString())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETTEAM", t.message.toString())

            }
        })
    }

    fun getCaptainTeams(token: String, callback: (List<String>) -> Unit) {
        val service = createTeamService()
        val call = service.getCaptainTeams(token)

        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val teamsResponse = response.body()
                    val teams = teamsResponse?.get("message") ?: emptyList()
                    callback(teams.sorted())
                } else {
                    Log.d("ErrorTeamsGetCaptainTeams", response.toString())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETTEAM", t.message.toString())

            }
        })


    }
}