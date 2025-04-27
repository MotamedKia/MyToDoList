package com.example.mytodolist.logic

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ExitDialog(modifier: Modifier = Modifier, onDismis: () -> Unit) {
    val activity = LocalActivity.current
    Dialog(onDismissRequest = { null }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Are you leaving?")
                    Spacer(Modifier.height(24.dp))
                    Row(Modifier.padding(horizontal = 6.dp)) {
                        Button(
                            onClick = { onDismis() },
                            modifier = Modifier.weight(1f)
                        ) { Text("No") }
                        Spacer(Modifier.width(12.dp))
                        Button(
                            onClick = { activity?.finish() },
                            modifier = Modifier.weight(1f)
                        ) { Text("Yes") }
                    }
                }
            }
        }
    }
}