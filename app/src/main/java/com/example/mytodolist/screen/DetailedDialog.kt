package com.example.mytodolist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mytodolist.R
import com.example.mytodolist.logic.ToDoItems
import com.example.mytodolist.screen.destinations.AddScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun DetailedDialog(
    modifier: Modifier = Modifier,
    toDoItem: ToDoItems,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit,
    onEditClick: () -> Unit,
    navigator: DestinationsNavigator
) {
    val scrollState = rememberScrollState()
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .size(300.dp, 350.dp),
            shape = RoundedCornerShape(90.dp),
            colors = if (toDoItem.importance) {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            } else {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    toDoItem.name.uppercase(),
                    color = if (toDoItem.importance) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                    fontFamily = FontFamily(Font(R.font.daphthello)),
                    fontSize = 50.sp
                )
                Spacer(Modifier.height(24.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(60.dp),
                    colors = if (toDoItem.importance) {
                        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(34.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            toDoItem.description, color = if (toDoItem.importance) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onPrimary
                            }, modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
                Row(
                    Modifier
                        .background(
                            color = if (toDoItem.importance) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { onConfirmClick() }) {
                        Icon(
                            Icons.Default.Delete,
                            "",
                            tint = if (toDoItem.importance) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    IconButton(onClick = {
                        onEditClick()
                        navigator.navigate(
                            AddScreenDestination(
                                name = toDoItem.name,
                                description = toDoItem.description,
                                importance = toDoItem.importance,
                                done = toDoItem.done
                            ).route
                        )
                    }) {
                        Icon(
                            Icons.Default.Edit,
                            "",
                            tint = if (toDoItem.importance) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}