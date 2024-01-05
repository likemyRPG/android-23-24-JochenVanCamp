package com.example.nameanalysis.ui.screens.resultscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nameanalysis.ui.components.GenderInfoCard
import java.util.Locale

/**
 * A Composable function that creates the result screen for the gender analysis.
 *
 * This screen displays the results of the gender analysis including the name analyzed, the predicted gender,
 * the probability of the prediction, and the count of data points used. It uses a Scaffold layout for consistent
 * theming and a TopAppBar with a back navigation icon. The results are displayed using the GenderInfoCard components
 * for a structured and visually appealing layout.
 *
 * @param navController The [NavController] used for navigation within the application.
 * @param name The name that was analyzed.
 * @param gender The gender result from the analysis.
 * @param probability The probability score of the gender prediction, displayed as a percentage.
 * @param count The number of occurrences analyzed to predict the gender.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenderResultScreen(
    navController: NavController, name: String, gender: String, probability: String, count: String
) {
    Scaffold(topBar = {
        // TopAppBar configuration with a back navigation icon
        TopAppBar(title = { Text("Gender Analysis Result") }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            // Content layout using Column and GenderInfoCard components
            val scrollState = rememberScrollState()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                GenderInfoCard("Name", name)
                GenderInfoCard("Gender",
                    gender.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                GenderInfoCard("Probability", "${probability.toDouble().times(100).toInt()}%")
                GenderInfoCard("Count", count)
            }
        }
    }
}

