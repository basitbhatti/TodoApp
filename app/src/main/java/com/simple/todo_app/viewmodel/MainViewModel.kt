package com.simple.todo_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.todo_app.db.Task
import com.simple.todo_app.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: TaskRepository) : ViewModel() {


    private val _currentTask = MutableLiveData<Task?>()

    val currentTask: LiveData<Task?>
        get() = _currentTask

    fun setCurrentTask(task: Task) {
        _currentTask.value = task
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun getTasks(): LiveData<List<Task>> {
        return repository.getTasks()
    }


}