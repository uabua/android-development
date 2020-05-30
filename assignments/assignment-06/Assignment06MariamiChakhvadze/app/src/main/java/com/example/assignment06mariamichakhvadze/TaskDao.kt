package com.example.assignment06mariamichakhvadze

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): MutableList<Task>

    @Query("SELECT * FROM task WHERE tid = :tid")
    fun getById(tid: Int): Task

    @Query("SELECT * FROM task ORDER BY tid DESC LIMIT 1")
    fun getLastById(): Task

    @Insert
    fun insertAll(vararg stories: Task)

    @Delete
    fun delete(story: Task)

    @Query("UPDATE task SET title = :title, description = :description WHERE tid = :tid")
    fun update(tid: Int, title: String, description: String)
}
