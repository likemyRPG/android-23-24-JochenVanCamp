// InputScreen.kt
package com.example.nameanalysis.ui.screens.inputscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModel
import kotlinx.coroutines.launch

@Composable
fun InputScreen(viewModel: NameAnalysisViewModel, navController: NavController) {
    var text by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter a Name") },
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    val genderResponse = viewModel.getGender(text)
                    navController.navigate("genderResult/${genderResponse.name}/${genderResponse.gender}/${genderResponse.probability}/${genderResponse.count}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(text = "Analyze Gender")
        }
    }
}