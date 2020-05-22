package com.example.midterm01mariamichakhvadze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()

        sendResetLinkButton.setOnClickListener {
            val email = emailResetLInkEditText.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter email to send reset link.", Toast.LENGTH_LONG)
                    .show()
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Reset link sent to your email. Check email!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else {
                            Toast.makeText(
                                this,
                                "Unable to send reset link, please, try again.",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    })
            }
        }

        backToLoginTextView.setOnClickListener {
            finish()
        }
    }
}
