package com.example.nameanalysis

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.nameanalysis.data.AppDataContainer
import com.example.nameanalysis.theme.NameAnalysisTheme

/**
 * The main activity of the Name Analysis application.
 *
 * This activity serves as the entry point to the application. It sets up the UI content using Jetpack Compose,
 * applies the application theme, and initializes the navigation controller. The activity also prepares the
 * dependency container and adjusts the soft input mode for better UI handling.
 */
class MainActivity : ComponentActivity() {
    /**
     * The container for application-wide dependencies.
     */
    private lateinit var appContainer: AppDataContainer

    /**
     * Called when the activity is starting.
     *
     * This function initializes the appContainer, adjusts the window's soft input mode, and sets the content view
     * for the activity using Compose UI elements. It applies the app theme and initializes the navigation graph.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState.
     *                           Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppDataContainer(this)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            NameAnalysisTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController, appContainer = appContainer)
                }
            }
        }
    }
}
