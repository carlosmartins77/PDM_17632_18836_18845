package com.example.myteamspage.Activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions
import com.example.myteamspage.Utils.ValidateParameters
import com.google.android.material.textfield.TextInputEditText

class CreateNewPassword : AppCompatActivity() {

    private lateinit var confirmPasswordEt: TextInputEditText
    private lateinit var passwordEt: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create_new_password)

        val userServiceFunctions = UserServiceFunctions()
        val continueBtn = findViewById<Button>(R.id.continueCreatePWDBtn)
        passwordEt = findViewById(R.id.passwordText)
        confirmPasswordEt = findViewById(R.id.confirmPasswordText)
        lateinit var email: String
        lateinit var password: String

        continueBtn.setOnClickListener {
            val passwordTemp = passwordEt.text.toString()
            val email = intent.getStringExtra("confirmCodeEmail").toString()
            val code = intent.getStringExtra("confirmCodeCode").toString()
            val confirmPassword = confirmPasswordEt.text.toString()
            if(passwordTemp == confirmPassword && passwordTemp != null) {
                val validateParameters = ValidateParameters("NoEmail", passwordTemp)

                if(validateParameters.isValidPassword(passwordTemp)){
                    password = passwordTemp
                }
                else{
                    Toast.makeText(applicationContext,passwordTemp, Toast.LENGTH_LONG).show()
                }
            }
            else if(passwordTemp != confirmPassword)
            {
                Toast.makeText(applicationContext,"PASSWORDS DON'T MATCH", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"ERROR", Toast.LENGTH_LONG).show()
            }
            //endregion

            if(password != null)
            {
                userServiceFunctions.forgotPasswordUser(this, email, code, password)
            }
        }
    }

    private fun showCustomDialogBox(){
        val dialogBinding = layoutInflater.inflate(R.layout.password_changed_successfully, null)
        val dialog = Dialog(this)

        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnContinue : Button = dialog.findViewById(R.id.pwdSuccessfullyBtn)

        btnContinue.setOnClickListener {
            dialog.dismiss()
        }
    }
}