package com.example.myteamspage.Services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GameService {

    @POST("game/schedule")
    fun scheduleGame(@Header("Authorization") token: String, @Body requestBody: Map<String, String>): Call<Map<String, String>>
}