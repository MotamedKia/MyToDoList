package com.example.mytodolist.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mytodolist.logic.ToDoItems
import com.example.mytodolist.screen.destinations.ListScreenDestination
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    var toDoListState by remember { mutableStateOf<List<ToDoItems>?>(null) }
    var showItemDialog by remember { mutableStateOf<ToDoItems?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        toDoListState = Hawk.get<List<ToDoItems>?>("ToDoes", emptyList()).toMutableList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(toDoListState ?: emptyList()) {
            MyToDoItem(toDoItems = it, listState = toDoListState, onDoneClick = {
                showItemDialog = it
            }, onDeleteClick = {
                deleteFunction(it, toDoListState, navigator, context)
            })
        }
    }
    showItemDialog?.let { item ->
        DetailedDialog(onConfirmClick = {
            deleteFunction(item, toDoListState, navigator, context)
            showItemDialog = null
        }, onDismiss = { showItemDialog = null }, toDoItem = item)
    }
}

private fun deleteFunction(
    item: ToDoItems,
    toDoListState: List<ToDoItems>?,
    navigator: DestinationsNavigator,
    context: Context
) {
    var toDoListState1 = toDoListState
    val updatedList: List<ToDoItems>? =
        Hawk.get("ToDoes", emptyList())
    val finalList = updatedList?.toMutableList()
    finalList?.remove(item)
    Hawk.put("ToDoes", finalList)
    toDoListState1 = Hawk.get("ToDoes", emptyList())
    navigator.navigate(ListScreenDestination)
    Toast.makeText(
        context,
        "Item Deleted",
        Toast.LENGTH_SHORT
    )
        .show()
}