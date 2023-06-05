package com.example.myteamspage

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @POST("auth/login")
    fun loginUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @PUT("auth/forgotpassword")
    fun forgotPasswordUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/signup")
    fun signUpUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/sendrecoverycode")
    fun sendRecoveryCodeUser(@Body requestBody: Map<String, String>): Call<Map<String, String>>

    @POST("auth/verifyresetcode")
    fun verifyResetCode(@Body requestBody: Map<String, String>): Call<Boolean>

    @GET("auth/getcountries")
    fun getCountries(): Call<List<String>>
}