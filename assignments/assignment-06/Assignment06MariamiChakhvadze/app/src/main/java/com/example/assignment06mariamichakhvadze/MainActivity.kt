package com.example.assignment06mariamichakhvadze

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val requestAddTask = 11
    private val requestEditTask = 21

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("task", Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        sharedPreferences.edit()
    }

    val tasks = mutableListOf<Task>()
    private lateinit var adapter: TaskRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        init()
    }

    private fun init() {
        if (!sharedPreferences.contains("initialization")) {
            AsyncTask.execute {
                db.taskDao()
                    .insertAll(Task("Create Task!", "Create tasks to be your day well organized!"))
                db.taskDao().insertAll(
                    Task(
                        "Delete Task",
                        "To delete task just press long on it."
                    )
                )
                db.taskDao().insertAll(
                    Task(
                        "See & Edit Task!",
                        "Press on the task to see details or edit task."
                    )
                )

                editor.putBoolean("initialization", true)
                editor.apply()
            }
        }

        AsyncTask.execute {
            tasks.addAll(db.taskDao().getAll())
        }

        Handler().postDelayed({
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = TaskRecyclerViewAdapter(tasks, this)
            recyclerView.adapter = adapter
        }, 500)
    }

    fun editTask(tid: Int, adapterPosition: Int) {
        val intent = Intent(this, EditTaskActivity::class.java)

        intent.putExtra("tid", tid)
        intent.putExtra("adapterPosition", adapterPosition)

        startActivityForResult(intent, requestEditTask)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == requestAddTask) {
            AsyncTask.execute {
                val task = db.taskDao().getLastById()

                runOnUiThread {
                    tasks.add(task)

                    noItemsTextView.visibility = View.GONE

                    adapter.notifyItemInserted(tasks.size - 1)
                    recyclerView.scrollToPosition(tasks.size - 1)
                }
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == requestEditTask) {
            val tid = data!!.getIntExtra("tid", 0)
            val adapterPosition = data.getIntExtra("adapterPosition", 0)

            intent.removeExtra("tid")
            intent.removeExtra("adapterPosition")

            AsyncTask.execute {
                val task = db.taskDao().getById(tid)

                runOnUiThread {
                    tasks[adapterPosition].title = task.title
                    tasks[adapterPosition].description = task.description

                    adapter.notifyItemChanged(adapterPosition)
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when (itemView) {
            R.id.addTaskButton -> {
                val intent = Intent(this, AddTaskActivity::class.java)

                startActivityForResult(intent, requestAddTask)
            }
        }

        return false
    }
}