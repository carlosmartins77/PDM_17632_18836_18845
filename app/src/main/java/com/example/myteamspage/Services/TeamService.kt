package com.example.myteamspage.Services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TeamService {
    @GET("teams/listallteams")
    fun getAllTeams(@Header("Authorization") token: String): Call<Map<String, List<String>>>

    @GET("teams/listteamscaptain")
    fun getCaptainTeams(@Header("Authorization") token: String): Call<Map<String, List<String>>>
}

