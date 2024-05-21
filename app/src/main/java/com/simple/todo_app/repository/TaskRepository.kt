package com.simple.todo_app.repository

import androidx.lifecycle.LiveData
import com.simple.todo_app.db.Dao
import com.simple.todo_app.db.Task

class TaskRepository(val dao: Dao) {

    suspend fun addTask(task: Task) {
        dao.addTask(task)
    }

    suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    fun getTasks(): LiveData<List<Task>> {
        return dao.getTasks()
    }

}