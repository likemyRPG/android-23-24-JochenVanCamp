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
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import com.example.nameanalysis.ui.screens.resultscreen.GenderResultScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val repository = NameAnalysisRepository()
    val viewModel: NameAnalysisViewModel = viewModel(factory = NameAnalysisViewModelFactory(repository))

    NavHost(navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            HomeScreen(onStartClick = { navController.navigate(NavRoutes.OPTIONS) })
        }
        composable(NavRoutes.OPTIONS) {
            OptionMenu(
                navController,
                onGenderClick = { navController.navigate(NavRoutes.INPUT) },
            )
        }
        composable(NavRoutes.INPUT) {
            InputScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            NavRoutes.GENDER_RESULT,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("probability") { type = NavType.StringType },
                navArgument("count") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val probability = backStackEntry.arguments?.getString("probability") ?: ""
            val count = backStackEntry.arguments?.getString("count") ?: ""

            GenderResultScreen(navController = navController, name, gender, probability, count)
        }
    }
}
