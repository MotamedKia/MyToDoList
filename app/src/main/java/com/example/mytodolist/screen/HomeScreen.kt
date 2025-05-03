package com.example.mytodolist.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodolist.R
import com.example.mytodolist.logic.RandomIndex
import com.example.mytodolist.logic.ToDoItems
import com.example.mytodolist.logic.TransformString
import com.example.mytodolist.logic.motiveList
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val motive by remember { mutableStateOf(motiveList) }
    var shuffleMotive by remember { mutableStateOf(motive.shuffled()) }
    var currentIndex by remember { mutableStateOf(0) }

    var toDoListState by remember { mutableStateOf<List<ToDoItems>?>(null) }
    val context = LocalContext.current
    var showSearchDialog by remember { mutableStateOf(false) }
    var searchInput by remember { mutableStateOf("") }

    toDoListState = Hawk.get<List<ToDoItems>?>("ToDoes", emptyList()).toMutableList()

    val itemNum by remember { mutableStateOf(toDoListState?.size) }
    val imporNum by remember { mutableStateOf(toDoListState?.filter { it.importance }?.size) }
    val doneNum by remember { mutableStateOf(toDoListState?.filter { it.done }?.size) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 36.dp, start = 36.dp, bottom = 54.dp, top = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            shuffleMotive[currentIndex].sayer.uppercase(),
            fontFamily = FontFamily(Font(R.font.daphthello)),
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            style = TextStyle(lineHeight = 45.sp),
            modifier = Modifier.clickable {
                showSearchDialog = true
                searchInput = shuffleMotive[currentIndex].sayer
            }
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                currentIndex++
                if (currentIndex >= shuffleMotive.size) {
                    shuffleMotive = motive.shuffled()
                    currentIndex = 0
                }
            },
            shape = RoundedCornerShape(50.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    shuffleMotive[currentIndex].quote,
                    fontFamily = FontFamily(Font(R.font.bonheur_royale)),
                    textAlign = TextAlign.Center,
                    fontSize = 55.sp,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp, vertical = 54.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedCard() {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("$itemNum Items")
                Text("$imporNum Important Items")
                Text("$doneNum Done")
            }
        }
    }
    if (showSearchDialog) {
        SearchDialog(
            searchInput = searchInput,
            onDismiss = { showSearchDialog = false },
            onConfirmClick = {
                if (searchInput != "The Programmer") {
                    val url =
                        "https://www.google.com/search?q=who+is+${TransformString(searchInput)}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                } else {
                    val url = "https://github.com/MotamedKia"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
                Toast.makeText(context, "Searching For $searchInput", Toast.LENGTH_SHORT)
                    .show()
                showSearchDialog = false
            })
    }
}