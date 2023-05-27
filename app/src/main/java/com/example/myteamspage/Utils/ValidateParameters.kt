package com.example.myteamspage.Utils

class ValidateParameters(val email: String, val password:String, ) {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun isValidPassword(password: String) : Boolean{
        return password.length > 6
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }

}