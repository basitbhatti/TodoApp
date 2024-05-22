package com.simple.todo_app.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    val title : String,
    val description : String,
    val dueDate : String,
    val dueTime : String,
    var isCompleted : Boolean
)