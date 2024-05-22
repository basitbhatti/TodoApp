package com.simple.todo_app.navigation

sealed class Screen (val route:String) {

    object HomeScreen : Screen(route = "HomeScreen")
    object AddTaskScreen : Screen(route = "AddTaskScreen")

}