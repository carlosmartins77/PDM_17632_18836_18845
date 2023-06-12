package com.example.myteamspage.Services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PublicationService {
    @POST("pub/createpub")
    fun postPublication(@Header("Authorization") token: String, @Body requestBody: Map<String, String>): Call<Map<String, String>>

    @GET("pub/listallpub")
    fun listallpub(@Header("Authorization") token: String): Call<Map<String, List<String>>>

    @GET("pub/listpubsbyuser")
    fun listpubsbyuser(@Header("Authorization") token: String): Call<Map<String, List<String>>>
}