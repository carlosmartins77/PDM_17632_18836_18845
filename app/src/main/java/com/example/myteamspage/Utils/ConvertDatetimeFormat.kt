package com.example.myteamspage.Utils

import java.text.SimpleDateFormat
import java.util.Locale

class ConvertDatetimeFormat {

    fun convertDateFormat(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }

    fun convertMongoDBDateFormat(dateString: String): String {
        val inputFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        // Remover o Hora padrao ocidental
            val formattedInputDate = dateString.replaceFirst("\\s*\\(.*\\)$".toRegex(), "")

        val date = inputFormat.parse(formattedInputDate)
        return outputFormat.format(date)
    }


}


