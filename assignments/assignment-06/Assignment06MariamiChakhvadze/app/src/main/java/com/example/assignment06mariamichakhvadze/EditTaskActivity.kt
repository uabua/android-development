package com.example.assignment06mariamichakhvadze

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_edit_task.*

class EditTaskActivity : AppCompatActivity() {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        init()
    }

    private fun init() {
        val tid = intent.getIntExtra("tid", 0)
        val adapterPosition = intent.getIntExtra("adapterPosition", 0)

        intent.removeExtra("tid")
        intent.removeExtra("adapterPosition")

        AsyncTask.execute {
            val task = db.taskDao().getById(tid)

            editTitleEditText.setText(task.title)
            editDescriptionEditText.setText(task.description)
        }

        editButton.setOnClickListener {
            val title = editTitleEditText.text.toString()
            val description = editDescriptionEditText.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                AsyncTask.execute {
                    db.taskDao().update(tid, title, description)
                }

                intent.putExtra("tid", tid)
                intent.putExtra("adapterPosition", adapterPosition)

                setResult(Activity.RESULT_OK, intent)

                finish()
            } else {
                Toast.makeText(this, "Please fill in all of the fields!", Toast.LENGTH_LONG).show()
            }
        }
    }
}