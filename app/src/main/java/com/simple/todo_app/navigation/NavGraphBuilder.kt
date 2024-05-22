package com.simple.todo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simple.todo_app.HomeScreen
import com.simple.todo_app.ui.screens.AddTaskScreen.AddTaskScreen
import com.simple.todo_app.viewmodel.MainViewModel

@Composable
fun NavGraphBuilder(navHostController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navHostController, viewModel = viewModel)
        }

        composable(Screen.AddTaskScreen.route) {
            AddTaskScreen(navController = navHostController, viewModel = viewModel)
        }


    }

}