package com.example.mytodolist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mytodolist.R

@Composable
fun SearchDialog(
    modifier: Modifier = Modifier, searchInput: String?,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
//TODO ui asking if to search or not
    Dialog(onDismissRequest = { onDismiss() }) {
        OutlinedCard() {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Do You wish to search the following question?",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Spacer(Modifier.height(12.dp))
                Card {
                    Text(
                        "\"Who is $searchInput?\"",
                        fontFamily = FontFamily(Font(R.font.mono_font)),
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.padding(5.dp)) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onDismiss() }
                    ) { Text("No") }
                    Spacer(Modifier.weight(0.25f))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onConfirmClick() }
                    ) { Text("Yes") }
                }
            }
        }
    }
}