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

/**
 * A Composable function that creates an option menu screen.
 *
 * This screen provides a user interface with options for different functionalities in the application.
 * It includes a top app bar with a title and options represented as buttons. Each button performs a specific
 * action, like initiating a gender analysis or navigating back. The screen is built using the Scaffold layout
 * for consistent theming and structure.
 *
 * @param navController The [NavController] used for navigating between screens.
 * @param onGenderClick A lambda function to be invoked when the 'Gender Analysis' button is clicked.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OptionMenu(
    navController: NavController,
    onGenderClick: () -> Unit
) {
    Scaffold(
        topBar = {
            // TopAppBar configuration
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
                // Column configuration and content
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

