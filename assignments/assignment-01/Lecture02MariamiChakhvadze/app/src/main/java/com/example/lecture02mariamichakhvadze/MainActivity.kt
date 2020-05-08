package com.example.lecture02mariamichakhvadze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        logInButton.setOnClickListener {
            if (emailEditText.text.toString().isNotBlank() && passwordEditText.text.toString().isNotBlank()) {
                Toast.makeText(this, "You have successfully logged in!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter Email and Password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
