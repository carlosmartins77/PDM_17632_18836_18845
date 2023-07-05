package com.example.myteamspage.Utils

import android.content.Context
import android.widget.Toast

class SharedPreferencesFuncs {
    fun saveData(context: Context, key:String, value:String){
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if(key.startsWith("TOKEN"))
        {
            editor.putString(key, "Bearer $value")
        }
        else {
            editor.putString(key, value)
        }
        editor.apply()

        Toast.makeText(context, "data saved", Toast.LENGTH_LONG).show()
    }

    fun loadData(context: Context, key: String): String?{
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString(key, null)

        return value
    }

}