package com.example.myteamspage.Services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Activities.*
import com.example.myteamspage.Activities.ForgotPassword.ConfirmCode
import com.example.myteamspage.Activities.ForgotPassword.CreateNewPassword
import com.example.myteamspage.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import com.example.myteamspage.Activities.ScheduleGames.ScheduleGame
import com.example.myteamspage.Activities.TeamsHome.TeamsHomePage

class UserServiceFunctions {
    val sharedPreferencesFuncs = SharedPreferencesFuncs()

    private fun createUserService(): UserService {
        val BASE_URL = "http://192.168.27.51:3000/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserService::class.java)
    }

    fun signUp(context: Context, email: String, password: String, fullName: String,  country: String, birthdate: String, phoneNumber: String) {
        val service = createUserService()

        val requestBody = mapOf(
            "email" to email,
            "password" to password,
            "fullname" to fullName,
            "country" to country,
            "birthdate" to birthdate,
            "phonenumber" to phoneNumber
        )

        val call = service.signUpUser(requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, Logo_KickOff::class.java)
                    context.startActivity(intent)
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG).show()
                } else {
                    Log.d("responsecodenestjs", response.toString())
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun sendRecoveryCodeUser(context: Context, email: String) {
        val service = createUserService()

        val requestBody = mapOf(
            "email" to email
        )

        val call = service.sendRecoveryCodeUser(requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, ConfirmCode::class.java)
                    intent.putExtra("forgotPwdEmail", email)
                    context.startActivity(intent)
                } else {
                    Log.d("responsecodenestjs", response.toString())
                    Toast.makeText(context, "Could not send OTP code", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Log.d("forgotPwdError", t.message.toString())
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun verifyResetCode(context: Context, email: String, resetCode: String) {
        val service = createUserService()

        val requestBody = mapOf(
            "email" to email,
            "resetcode" to resetCode
        )

        val call = service.verifyResetCode(requestBody)

        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful && response.body() == true) {
                    val intent = Intent(context, CreateNewPassword::class.java)
                    intent.putExtra("confirmCodeEmail", email)
                    intent.putExtra("confirmCodeCode", resetCode)
                    context.startActivity(intent)
                } else {
                    Log.d("responsecodenestjs", response.toString())
                    Toast.makeText(context, "Invalid reset code", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                t.printStackTrace()
                Log.d("confirmCodeError", t.message.toString())
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun forgotPasswordUser(context: Context, email: String, code: String, newPassword: String) {
        val service = createUserService()

        val requestBody = mapOf(
            "email" to email,
            "code" to code,
            "newpassword" to newPassword
        )

        val call = service.forgotPasswordUser(requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, LoginActivity::class.java)
                    Toast.makeText(context, "Password changed successfully", Toast.LENGTH_LONG)
                        .show()
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Error updating password", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun getAllCountries(callback: (List<String>) -> Unit) {
        val service = createUserService()
        val call = service.getCountries()

        call.enqueue(object : Callback<Map<String, List<String>>> {
            override fun onResponse(
                call: Call<Map<String, List<String>>>,
                response: Response<Map<String, List<String>>>
            ) {
                if (response.isSuccessful) {
                    val countriesResponse = response.body()
                    val countries = countriesResponse?.get("message") ?: emptyList()

                    callback(countries.sorted())
                } else {
                    Log.d("ErrorGetAllCountries", response.message())
                }
            }

            override fun onFailure(call: Call<Map<String, List<String>>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun login(context: Context, email: String, password: String) {
        val service = createUserService()

        val requestBody = mapOf(
            "email" to email,
            "password" to password
        )

        val call = service.login(requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()?.get("token")

                    // Save token on shared preferences
                    sharedPreferencesFuncs.saveData(context, "TOKEN_KEY", token.toString())
                    sharedPreferencesFuncs.saveData(context, "USERNAME_KEY", email.toString())
                    //region Start Schedule Game Activity
                    val intent = Intent(context, TeamsHomePage::class.java)
                    context.startActivity(intent)
                    //endregion

                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                } else {
                    //Log.d("responserequest", response.toString())
                    Toast.makeText(context, "Wrong email or password", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}