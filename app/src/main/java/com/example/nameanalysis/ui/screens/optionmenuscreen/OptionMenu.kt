package com.example.nameanalysis.ui.screens.optionmenuscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nameanalysis.ui.components.OptionButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OptionMenu(
    navController: NavController,
    onGenderClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Options") },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OptionButton("Gender Analysis", onGenderClick)
                Spacer(modifier = Modifier.height(16.dp))
                OptionButton("Go Back") { navController.navigateUp() }
            }
        }
    }
}

