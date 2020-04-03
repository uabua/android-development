package com.example.assignment02mariamichakhvadze

import android.app.Activity
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_second.*
import java.text.SimpleDateFormat
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        init()
    }

    private fun init() {

        val calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val pattern = "yyyy-MM-dd"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)

            birthDateTextView.text = simpleDateFormat.format(calendar.time)
        }

        birthDateTextView.setOnClickListener { DatePickerDialog(
            this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

        }


        saveButton.setOnClickListener {
            saveDetails()
        }
    }

    private fun saveDetails() {
        val intent = intent

        val userModel = userModel(fullNameEditText.text.toString(),
            emailEditText.text.toString(),
            birthDateTextView.text.toString(),
            findViewById<RadioButton>(radioGender.checkedRadioButtonId).text.toString()
            )
        intent.putExtra("userModel", userModel)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}
