package com.example.assignment03mariamichakhvadze

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        addButton.setOnClickListener {
            addItem()
        }
    }

    private fun addItem() {
        val intent = intent
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val userModel = UserModel(
            R.drawable.avatar,
            titleEditText.text.toString(),
            descriptionEditText.text.toString(),
            date
        )

        intent.putExtra("userModel", userModel)
        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}
