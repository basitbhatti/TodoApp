package com.simple.todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.simple.todo_app.db.Task
import com.simple.todo_app.db.TodoDatabase
import com.simple.todo_app.navigation.NavGraphBuilder
import com.simple.todo_app.navigation.Screen
import com.simple.todo_app.repository.TaskRepository
import com.simple.todo_app.ui.theme.ToDoAppTheme
import com.simple.todo_app.viewmodel.MainViewModel
import com.simple.todo_app.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {

                val repo = TaskRepository(TodoDatabase.getInstance(this@MainActivity).getDao())

                val viewModel =
                    ViewModelProvider(this, ViewModelFactory(repo)).get(MainViewModel::class.java)

                val navController = rememberNavController()
                NavGraphBuilder(navHostController = navController, viewModel)

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController, viewModel: MainViewModel) {

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

            LazyColumn {
                items(listTasksDue) {
                    TaskDueItem(task = it, navController = navController)
                }
            }


        }
    }
}


@Preview
@Composable
private fun HomePreview() {
    val nav = rememberNavController()
}

@Composable
fun TaskDueItem(task: Task, navController: NavHostController) {

    val viewModel: MainViewModel = viewModel<MainViewModel>()

    var isCompleted by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier.clickable {
            viewModel.setCurrentTask(task)
            navController.navigate(Screen.AddTaskScreen.route)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 15.dp, vertical = 7.5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(checked = isCompleted,
                onCheckedChange = {
                    isCompleted = it

                    task.isCompleted = it
                    viewModel.updateTask(task)

                })

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(text = task.title)
                Text(text = task.dueDate, color = Color.Gray, fontSize = 10.sp)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskPreview() {
}