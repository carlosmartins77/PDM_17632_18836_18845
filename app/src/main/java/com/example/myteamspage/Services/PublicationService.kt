package com.example.myteamspage.Services

import com.example.myteamspage.Classes.Publication
import com.example.myteamspage.Classes.PublicationResponse
import com.example.myteamspage.Classes.SQLPublication
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PublicationService {
    @POST("pub/createpub")
    fun postPublication(@Header("Authorization") token: String, @Body requestBody: Map<String, String>): Call<Map<String, String>>

    @GET("pub/listallpub")
    fun listAllPubs(@Header("Authorization") token: String): Call<PublicationResponse>

    @GET("pub/listpubsbyuser")
    fun listpubsbyuser(@Header("Authorization") token: String): Call<List<SQLPublication>>
}
