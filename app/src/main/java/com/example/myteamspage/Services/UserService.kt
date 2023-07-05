package com.example.myteamspage

import com.example.myteamspage.Classes.PublicationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {
    @POST("auth/login")
    fun login(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @PUT("auth/forgotpassword")
    fun forgotPasswordUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/signup")
    fun signUpUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/sendrecoverycode")
    fun sendRecoveryCodeUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/verifyresetcode")
    fun verifyResetCode(@Body requestBody: Map<String, String>): Call<Boolean>

    @GET("auth/getcountries")
    fun getCountries(): Call<Map<String, List<String>>>

    @GET("auth/userinformation")
    fun getUserInformation(@Query("email") email: String): Call<Map<String, Map<String, Any>>>

    @GET("auth/getEmailByToken")
    fun getEmailByToken(@Header("Authorization") token: String): Call<Map<String, String>>


}