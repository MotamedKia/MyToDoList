package com.example.mytodolist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mytodolist.logic.ToDoItems
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    var toDoListState by remember { mutableStateOf<List<ToDoItems>?>(null) }

    LaunchedEffect(Unit) {
        toDoListState = Hawk.get<List<ToDoItems>?>("ToDoes", emptyList()).toMutableList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(toDoListState ?: emptyList()) {
            MyToDoItem(toDoItems = it, listState = toDoListState)
        }
    }
}