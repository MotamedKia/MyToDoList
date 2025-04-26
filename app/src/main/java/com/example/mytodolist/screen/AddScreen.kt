package com.example.mytodolist.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodolist.R
import com.example.mytodolist.logic.SplitString
import com.example.mytodolist.logic.ToDoItems
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddScreen(modifier: Modifier = Modifier) {
    var itemName by rememberSaveable { mutableStateOf("") }
    var itemDescription by rememberSaveable { mutableStateOf("") }
    var itemImportance by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            SplitString(itemName.lowercase(), 6),
            fontSize = 100.sp,
            fontFamily = FontFamily(Font(R.font.diamond)),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            style = TextStyle(lineHeight = 24.sp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 36.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Name") },
            maxLines = 1
        )
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = itemDescription,
            onValueChange = { itemDescription = it },
            label = { Text("Description") })
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Important")
            Spacer(Modifier.height(55.dp))
            IconButton(onClick = { itemImportance = !itemImportance }) {
                Icon(if (itemImportance) Icons.Default.Done else Icons.Default.Close, "")
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                val dbList: List<ToDoItems>? = Hawk.get("ToDoes", emptyList())
                val finalList = dbList?.toMutableList()
                finalList?.add(ToDoItems(itemName, itemDescription, itemImportance, false))
                Hawk.put("ToDoes", finalList)
                Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show()
                itemName = ""
                itemDescription = ""
                itemImportance = false
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("ADD") }
    }
}