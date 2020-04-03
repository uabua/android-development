package com.example.assignment02mariamichakhvadze

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        editButton.setOnClickListener {
            openSecondActivity()
        }
    }

    private fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val userModel = data?.extras?.getParcelable<userModel>("userModel")

            fullNameTextView.text = userModel?.fullName
            emailTextView.text = userModel?.email
            birthdayTextView.text = userModel?.birthday
            genderTextView.text = userModel?.gender
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
