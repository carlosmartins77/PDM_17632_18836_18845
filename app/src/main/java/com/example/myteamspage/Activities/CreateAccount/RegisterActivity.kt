
package com.example.myteamspage.Activities.CreateAccount


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myteamspage.R
import com.example.myteamspage.Utils.ValidateParameters
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var confirmPasswordEt: TextInputEditText
    private lateinit var passwordEt: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val continueButton = findViewById<Button>(R.id.registerContinueBtn)
        val emailEt = findViewById<EditText>(R.id.registerEmailEt)
        passwordEt = findViewById(R.id.passwordText)
        confirmPasswordEt = findViewById(R.id.confirmPasswordText)
        val arrowBack = findViewById<ImageView>(R.id.register_arrow_icon)
        val progressBar = findViewById<ProgressBar>(R.id.register_progress_bar)
        lateinit var email: String
        lateinit var password: String

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 75


        continueButton.setOnClickListener {
            val emailTemp = emailEt.text.toString()
            val passwordTemp = passwordEt.text.toString()
            val confirmPassword = confirmPasswordEt.text.toString()

            //region Register validations
            if(passwordTemp == confirmPassword && passwordTemp != null && emailTemp != null) {
                val validateParameters = ValidateParameters(emailTemp, passwordTemp)

                if(validateParameters.isValidEmail(emailTemp)){
                    email = emailTemp
                }
                else{
                    Toast.makeText(applicationContext,"EMAIL INVALID!", Toast.LENGTH_LONG).show()
                }

                if(validateParameters.isValidPassword(passwordTemp)){
                    password = passwordTemp
                }
                else{
                    Toast.makeText(applicationContext,passwordTemp, Toast.LENGTH_LONG).show()
                }
            }
            else if(passwordTemp != confirmPassword)
            {
                Toast.makeText(applicationContext,"PASSWORDS DONT MATCH", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(applicationContext,"ERROR", Toast.LENGTH_LONG).show()
            }
           //endregion

            if(email != null && password != null)
            {
                val intent = Intent(this@RegisterActivity, CompleteYourProfile::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                startActivity(intent)
            }

        }

        arrowBack.setOnClickListener {
            val intent = Intent(this@RegisterActivity, Create_your_team_register::class.java)
            startActivity(intent)
        }
    }
}