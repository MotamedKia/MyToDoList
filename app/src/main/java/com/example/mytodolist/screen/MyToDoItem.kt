package com.example.mytodolist.screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytodolist.R
import com.example.mytodolist.logic.ToDoItems
import com.orhanobut.hawk.Hawk

@Composable
fun MyToDoItem(
    modifier: Modifier = Modifier,
    toDoItems: ToDoItems,
    listState: List<ToDoItems>?,
    onDoneClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val context = LocalContext.current
    var isDone by rememberSaveable { mutableStateOf(toDoItems.done) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(end = 8.dp, bottom = 8.dp, top = 8.dp, start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Crossfade(isDone) {
            IconButton(onClick = {
                isDone = !isDone
                listState?.find { it.name == toDoItems.name }?.done = isDone
                Hawk.put("ToDoes", listState)
            }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(
                    painter = if (it) painterResource(
                        R.drawable.baseline_check_circle_24
                    ) else painterResource(
                        R.drawable.baseline_circle_24
                    ),
                    "",
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = if (it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                )
            }
        }
//        Spacer(Modifier.width(8.dp))
        Card(
            modifier
                .weight(1f)
                .clickable {
                    onDoneClick()
                },
            colors = if (toDoItems.importance) {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
            } else CardDefaults.cardColors(),
            shape = if (toDoItems.importance) {
                RoundedCornerShape(50.dp)
            } else RoundedCornerShape(15.dp)
        ) {
            Row(
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(12.dp))
                Text(toDoItems.name.capitalize())
            }
        }
        if (isDone) {
//            Spacer(Modifier.width(12.dp))
            IconButton(onClick = {
                onDeleteClick()
            }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(
                    painter = painterResource(R.drawable.baseline_delete_24),
                    "",
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } /*else {
           Spacer(modifier = Modifier.padding(34.dp))
       }*/
    }
}