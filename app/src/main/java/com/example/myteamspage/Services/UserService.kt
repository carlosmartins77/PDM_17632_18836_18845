package com.example.myteamspage

import com.example.myteamspage.Entities.LoginDto
import com.example.myteamspage.Entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("auth/login")
    fun loginUser(@Body loginDto: LoginDto): Call<Map<String, String>>
}