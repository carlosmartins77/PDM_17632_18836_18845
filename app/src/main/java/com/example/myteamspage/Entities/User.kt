package com.example.myteamspage.Entities

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class User(@SerializedName("email") val userId: String,
                @SerializedName("password") val id: String) {


}