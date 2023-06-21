package com.example.myteamspage.Services

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myteamspage.Activities.PublicationScreen
import com.example.myteamspage.Classes.PublicationImage
import com.example.myteamspage.Classes.PublicationResponse
import com.example.myteamspage.Classes.SQLPublication
import com.example.myteamspage.Classes.SQLitePublication
import com.example.myteamspage.Utils.SharedPreferencesFuncs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PublicationServiceFunction {

    val sharedPreferencesFuncs = SharedPreferencesFuncs()

    private fun createPublicationService(): PublicationService {
        val BASE_URL = "http://192.168.27.51:7070/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PublicationService::class.java)
    }

    fun postPublication(context: Context, token: String, username: String, content: String) {
        val service = createPublicationService()

        val requestBody = mapOf(
            "username" to username,
            "content" to content,
        )

        val call = service.postPublication(token, requestBody)

        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(context, PublicationScreen::class.java)
                    context.startActivity(intent)
                    Toast.makeText(context, "Publication created successful", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Log.d("ERROPUB:", response.toString())
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun listpubsbyuser(context: Context, token: String) {
        val service = createPublicationService()
        val call = service.listpubsbyuser(token)
        call.enqueue(object : Callback<List<SQLPublication>> {
            override fun onResponse(call: Call<List<SQLPublication>>, response: Response<List<SQLPublication>>) {
                if (response.isSuccessful) {
                    try {
                        val pubResponse = response.body()
                        val pub = pubResponse ?: emptyList()
                        Log.d("PUBLICATIONSERVICEFUNCTION", pub.toString())

                        // Store the values in SQLite database
                        val databaseHelper = SQLitePublication(context)
                        val db = databaseHelper.writableDatabase

                        // Create the table if it doesn't exist
                        val createTableQuery = "CREATE TABLE IF NOT EXISTS publications (COLUMN_USERNAME TEXT, COLUMN_CONTENT TEXT, COLUMN_DATE TEXT)"
                        db.execSQL(createTableQuery)

                        db.delete("TABLE_NAME", null, null)
                        // Iterate over the publications and insert them into the database
                        pub.forEach { publication ->
                            val values = ContentValues().apply {
                                put("COLUMN_USERNAME", publication.username)
                                put("COLUMN_CONTENT", publication.content)
                                put("COLUMN_DATE", publication.createdAt)
                            }
                            db.insert("TABLE_NAME", null, values)  // Updated here
                        }
                        db.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.d("EXCEPTION", e.message.toString())
                    }
                } else {
                    Log.d("ErrorPublicationGetByUser", response.toString())
                }
            }

            override fun onFailure(call: Call<List<SQLPublication>>, t: Throwable) {
                t.printStackTrace()
                Log.d("FAILUREGETPUBLICATION", t.message.toString())
            }
        })
    }

    fun listAllPubs(context: Context, token: String, callback: (List<PublicationImage>) -> Unit) {
        val service = createPublicationService()
        val call = service.listAllPubs(token)
        call.enqueue(object : Callback<PublicationResponse> {
            override fun onResponse(call: Call<PublicationResponse>, response: Response<PublicationResponse>) {
                if (response.isSuccessful) {
                    val pubResponse = response.body()?.message
                    Log.d("OkPublicationGetAllPubs", pubResponse.toString())
                    if (pubResponse != null) {
                        callback(pubResponse)
                    }
                } else {
                    Log.d("ErrorPublicationGetAllPubs", response.toString())
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<PublicationResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("ErrorFailurePublicationGetAllPubs", t.message.toString())
                callback(emptyList())
            }
        })
    }
}