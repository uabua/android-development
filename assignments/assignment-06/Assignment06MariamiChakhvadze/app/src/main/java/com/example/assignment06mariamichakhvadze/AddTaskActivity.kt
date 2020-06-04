package com.example.assignment06mariamichakhvadze

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setSupportActionBar(addToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        addButton.setOnClickListener {
            val title = addTitleEditText.text.toString()
            val description = addDescriptionEditText.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                AsyncTask.execute {
                    val task = Task(title, description)
                    db.taskDao().insertAll(task)
                }

                setResult(Activity.RESULT_OK)

                finish()
            } else {
                Toast.makeText(this, "Please fill in all of the fields!", Toast.LENGTH_LONG).show()
            }
        }
    }
}