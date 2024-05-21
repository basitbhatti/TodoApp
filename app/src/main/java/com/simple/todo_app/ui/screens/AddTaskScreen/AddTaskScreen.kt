package com.simple.todo_app.ui.screens.AddTaskScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTaskScreen(modifier: Modifier = Modifier) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var dueDate by remember {
        mutableStateOf("")
    }

    var isCompleted by remember {
        mutableStateOf(false)
    }


    Column(modifier = modifier.fillMaxSize()) {

        Scaffold(topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { /*TODO BACK BUTTON*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ), title = { Text(text = "Add Note Screen") })
        }, floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(15.dp),
                onClick = { /*TODO FLOATING ACTION BUTTON*/ }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Icon")
            }
        }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = title,
                    maxLines = 1,
                    onValueChange = { title = it },
                    label = { Text(text = "Task") },
                    placeholder = {
                        Text(
                            text = "Enter Task"
                        )
                    })

                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") },
                    placeholder = {
                        Text(
                            text = "Enter Description"
                        )
                    })


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .height(50.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .fillMaxHeight(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )
                    ) {

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Icon (
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Date Icon",
                                modifier = Modifier.size(30.dp)
                            )

                            Spacer(modifier = Modifier.weight(10.dp))

                            Text(text = "Pick Date", fontSize = 18.sp)


                        }

                    }


                }


            }

        }

    }


}


@Preview
@Composable
private fun TaskPrev() {
    AddTaskScreen()
}