package com.example.nameanalysis

import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModelFactory
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nameanalysis.ui.screens.homescreen.HomeScreen
import com.example.nameanalysis.ui.screens.inputscreen.InputScreen
import com.example.nameanalysis.ui.screens.optionmenuscreen.OptionMenu
import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import com.example.nameanalysis.data.AppContainer
import com.example.nameanalysis.ui.screens.resultscreen.GenderResultScreen

/**
 * A Composable function that sets up the navigation host for the application.
 *
 * This function uses the Jetpack Navigation component to define the navigation graph for the app.
 * It sets up different screens (composables) for navigation and associates them with corresponding routes.
 * It also handles the creation and provision of the ViewModel to the screens that require it.
 *
 * @param navController The [NavHostController] that controls the navigation among composables.
 * @param appContainer The [AppContainer] providing dependencies for the application, such as repositories.
 */
@Composable
fun AppNavHost(navController: NavHostController, appContainer: AppContainer) {
    val viewModelFactory = NameAnalysisViewModelFactory(appContainer.nameAnalysisRepository)

    NavHost(navController, startDestination = NavRoutes.HOME) {
        // Definition of composable screens and their navigation routes
        composable(NavRoutes.HOME) {
            // HomeScreen composable with navigation logic
            HomeScreen(onStartClick = { navController.navigate(NavRoutes.OPTIONS) })
        }
        composable(NavRoutes.OPTIONS) {
            // OptionMenu composable with navigation logic
            OptionMenu(
                navController,
                onGenderClick = { navController.navigate(NavRoutes.INPUT) },
            )
        }
        composable(NavRoutes.INPUT) {
            // InputScreen composable with ViewModel and navigation controller
            val viewModel: NameAnalysisViewModel = viewModel(factory = viewModelFactory)
            InputScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            NavRoutes.GENDER_RESULT,
            arguments = listOf(
                // Navigation arguments for the GenderResultScreen
                navArgument("name") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("probability") { type = NavType.StringType },
                navArgument("count") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // GenderResultScreen composable with navigation arguments
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val probability = backStackEntry.arguments?.getString("probability") ?: ""
            val count = backStackEntry.arguments?.getString("count") ?: ""

            // GenderResultScreen composable with navigation logic
            GenderResultScreen(navController = navController, name, gender, probability, count)
        }
    }
}
