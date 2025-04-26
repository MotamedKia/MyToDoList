package com.example.mytodolist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun DetailedDialog(
    modifier: Modifier = Modifier,
    toDoItem: ToDoItems,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val scrollState = rememberScrollState()
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .size(400.dp, 400.dp),
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
                            }
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = { onConfirmClick() },
                    colors = if (toDoItem.importance) {
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onError,
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    } else {
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    }
                ) {
                    Text("Delete")
                }
            }
        }
    }
}