package com.example.myteamspage.Activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myteamspage.R
import com.google.android.material.textfield.TextInputEditText

class CreateNewPassword : AppCompatActivity() {

    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create_new_password)

        val continueBtn = findViewById<Button>(R.id.continueCreatePWDBtn)
        passwordEditText = findViewById(R.id.passwordText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordText)

        continueBtn.setOnClickListener {
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            // validar se as passwords são iguais ou não
            if( password != confirmPassword){
                Toast.makeText(applicationContext,"PASSWORD´S ARE DIFFERENT!",Toast.LENGTH_LONG).show()
            } else if(password.isEmpty() && confirmPassword.isEmpty()){
                Toast.makeText(applicationContext,"INSERT THE PASSWORD´S FIELDS",Toast.LENGTH_LONG).show()
            }
            else{
                //mostrar o alerta da password alterada
                showCustomDialogBox()
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