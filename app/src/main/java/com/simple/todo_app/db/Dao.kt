package com.simple.todo_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface Dao {

    @Upsert
    suspend fun addTask(task : Task)
    @Update
    suspend fun updateTask(task : Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task")
    fun getTasks() : LiveData<List<Task>>

}