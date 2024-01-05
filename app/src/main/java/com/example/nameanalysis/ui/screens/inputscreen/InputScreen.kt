package com.example.nameanalysis.ui.screens.inputscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModel
import kotlinx.coroutines.launch

/**
 * A Composable function that creates the input screen for the name analysis application.
 *
 * This screen includes a text field for entering a name, a button for submitting the name for analysis,
 * and displays a loading indicator or error messages as needed. It also handles navigation to the results
 * screen once the analysis is complete.
 *
 * @param viewModel The [NameAnalysisViewModel] that provides the business logic and data for this screen.
 * @param navController The [NavController] used for navigating between screens.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputScreen(viewModel: NameAnalysisViewModel, navController: NavController) {
    var text by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val maxNameLength = 20

    val apiErrorMessage by viewModel.errorMessage.asFlow().collectAsState(initial = null)

    val genderResponse by viewModel.genderResponse.asFlow().collectAsState(initial = null)

    val isLoading by viewModel.isLoading.asFlow().collectAsState(initial = false)

    Scaffold(topBar =
    {
        // TopAppBar with a back navigation icon
        TopAppBar(title = { Text("Name Analysis") }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            if (isLoading || genderResponse != null) {
                // Show CircularProgressIndicator while loading or when genderResponse is available
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else {
                // Input form for name analysis
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            if (it.length <= maxNameLength) {
                                text = it
                                errorMessage = when {
                                    it.isBlank() -> "Please enter a name"
                                    it.contains(" ") -> "No spaces allowed"
                                    else -> null
                                }
                            } else {
                                errorMessage = "Name too long (max 20 characters)"
                            }
                        },
                        label = { Text("Enter a Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        isError = errorMessage != null,
                        singleLine = true
                    )
                    errorMessage?.let {
                        Text(
                            it,
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 2.dp)
                        )
                    }

                    apiErrorMessage?.let {
                        Text(
                            it,
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (errorMessage == null) {
                                coroutineScope.launch {
                                    viewModel.getGender(text)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        enabled = text.isNotBlank() && !text.contains(" ") && errorMessage == null && text.length <= maxNameLength
                    ) {
                        Text(
                            text = "Analyze Gender",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }

    // Logic for navigating to the result screen when gender analysis response is received
    genderResponse?.let { response ->
        val genderString = response.gender ?: "Unknown"
        val probabilityString = response.probability.toString()
        val countString = response.count.toString()
        navController.navigate("genderResult/${response.name}/$genderString/$probabilityString/$countString")
        viewModel.clearGenderResponse() // Reset de LiveData na navigatie
    }
}