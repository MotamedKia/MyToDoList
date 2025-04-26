package com.example.mytodolist.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodolist.R
import com.example.mytodolist.logic.RandomIndex
import com.example.mytodolist.logic.motiveList
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var motive by remember { mutableStateOf(motiveList[RandomIndex(motiveList)]) }
    var itemNum by remember { mutableStateOf(0) }
    var imporNum by remember { mutableStateOf(0) }
    var doneNum by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { motive = motiveList[RandomIndex(motiveList)] },
            shape = RoundedCornerShape(50.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    motive,
                    fontFamily = FontFamily(Font(R.font.bonheur_royale)),
                    textAlign = TextAlign.Center, fontSize = 55.sp,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
        }
        Spacer(Modifier.height(40.dp))
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
}