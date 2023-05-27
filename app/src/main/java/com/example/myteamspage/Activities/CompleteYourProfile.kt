package com.example.myteamspage.Activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myteamspage.R
import com.example.myteamspage.Services.UserServiceFunctions
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class CompleteYourProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_your_profile)

        val progressBar = findViewById<ProgressBar>(R.id.complete_your_profile_progress_bar)
        val userServiceFunctions = UserServiceFunctions()
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val fullNameEt = findViewById<EditText>(R.id.complete_your_profile_full_name_field)
        val birthDateEt = findViewById<EditText>(R.id.complete_your_profile_date_of_birth_field)
        val phoneNumberEt = findViewById<EditText>(R.id.complete_your_profile_phone_number_field)
        val countryEt = findViewById<TextInputLayout>(R.id.dropdown_layout)
        val completeProfileContinueBtn = findViewById<Button>(R.id.complete_your_profile_ContinueBtn)
        val pickDateBtn = findViewById<Button>(R.id.complete_your_profile_pick_dateBtn)

        completeProfileContinueBtn.setOnClickListener {
            showCustomDialogBox()
        }

        //Adicionar as opções à combo para escolher o pais
        val options = listOf("Alemanha", "Espanha", "Portugal", "Ucrania")
        val dropdownAutoCompleteTextView: AutoCompleteTextView = findViewById(R.id.dropdown)
        val dropdownLayoutAutoCompleteTextView: TextInputLayout = findViewById(R.id.dropdown_layout)

        val adapter = ArrayAdapter(this, R.layout.list_item, options)
        dropdownAutoCompleteTextView.setAdapter(adapter)

        dropdownLayoutAutoCompleteTextView.setEndIconOnClickListener {
            dropdownAutoCompleteTextView.setText("", false)
        }

        pickDateBtn.setOnClickListener{
            showDatePicker(birthDateEt)
        }

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 100

        completeProfileContinueBtn.setOnClickListener{
            userServiceFunctions.signUp(this, email.toString()
                , password.toString()
                , fullNameEt.text.toString()
                , countryEt.toString()
                , birthDateEt.text.toString()
                , phoneNumberEt.text.toString())
        }


    }

    private fun showDatePicker(editTextDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            editTextDate.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showCustomDialogBox(){
        val dialogBinding = layoutInflater.inflate(R.layout.account_setup_successful, null)
        val dialog = Dialog(this)

        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

}
