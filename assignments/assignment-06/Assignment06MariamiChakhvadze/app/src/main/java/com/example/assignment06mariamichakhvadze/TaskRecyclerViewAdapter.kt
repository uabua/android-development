package com.example.assignment06mariamichakhvadze

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_recyclerview_layout.view.*

class TaskRecyclerViewAdapter(
    private val tasks: List<Task>,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var task: Task

        fun onBind() {
            task = tasks[adapterPosition]

            itemView.titleTextView.text = task.title

            itemView.setOnClickListener {
                activity.editTask(task.tid, adapterPosition)
            }

            itemView.setOnLongClickListener {
                activity.tasks.removeAt(adapterPosition)

                notifyItemRemoved(adapterPosition)

                AsyncTask.execute {
                    activity.db.taskDao().delete(task)
                }

                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task_recyclerview_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}