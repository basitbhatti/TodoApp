package com.simple.todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.simple.todo_app.db.Task
import com.simple.todo_app.navigation.Screen
import com.simple.todo_app.ui.theme.ToDoAppTheme
import com.simple.todo_app.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {

                val navController = rememberNavController()


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val viewModel: MainViewModel = viewModel<MainViewModel>()

    var listTasksDue by remember {
        mutableStateOf(mutableListOf<Task>())
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ), title = { Text(text = "Todo App") })
    }, floatingActionButton = {
        FloatingActionButton(modifier = Modifier.padding(15.dp), onClick = {
            navController.navigate(Screen.AddTaskScreen.route)
        }) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "Add Note"
            )
        }
    }

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            viewModel.getTasks().observe(LocalLifecycleOwner.current) { list ->

                list.forEach {
                    if (!it.isCompleted) {
                        listTasksDue.add(it)
                    }
                }


            }

        }
    }
}


@Preview
@Composable
private fun HomePreview() {
    val nav = rememberNavController()
    HomeScreen(navController = nav)
}

@Composable
fun TaskDueItem(modifier: Modifier = Modifier, task: Task) {

}


@Preview
@Composable
private fun TaskPreview() {
    TaskDueItem(task = Task(1, "Title here", "Description here", "10/10/24", "12:29", false))
}