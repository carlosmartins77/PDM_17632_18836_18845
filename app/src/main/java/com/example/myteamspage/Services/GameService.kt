package com.example.myteamspage.Services

import com.example.myteamspage.Classes.PublicationResponse
import com.example.myteamspage.Classes.TeamsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GameService {
    @POST("games/schedule")
    fun scheduleGame(@Header("Authorization") token: String, @Body requestBody: Map<String, String>): Call<Map<String, String>>

    @GET("games/listallgames")
    fun listAllUserGames(@Header("Authorization") token: String): Call<TeamsResponse>
}