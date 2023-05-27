package com.example.myteamspage.Activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myteamspage.R
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class CompleteYourProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_your_profile)

        val progressBar = findViewById<ProgressBar>(R.id.complete_your_profile_progress_bar)
        val continueBtn = findViewById<Button>(R.id.complete_your_profile_ContinueBtn)
        val editTextDate = findViewById<EditText>(R.id.complete_your_profile_date_of_birth_field)

        // Put the progress we what
        progressBar.max = 100
        progressBar.progress = 100

        editTextDate.setOnClickListener {
            showDatePicker(editTextDate)
        }

        continueBtn.setOnClickListener {
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

    }

    //Apresenta a data selecionada do datePicker na label
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

    //apresentar aviso de conta criada com sucesso
    private fun showCustomDialogBox(){
        val dialogBinding = layoutInflater.inflate(R.layout.account_setup_successful, null)
        val dialog = Dialog(this)

        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}