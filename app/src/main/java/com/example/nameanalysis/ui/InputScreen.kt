package com.example.nameanalysis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputScreen(onSearchClick: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") },
            modifier = Modifier.padding(8.dp)
        )
        Button(onClick = { onSearchClick(text) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Search")
        }
    }
}
