package com.example.nameanalysis.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(onStartClick = { navController.navigate("options") }) }
        composable("options") {
            OptionMenu(
                navController,
                onGenderClick = { /* TODO: Navigate to gender feature */ },
                onNationalityClick = { /* TODO: Navigate to nationality feature */ }
            )
        }
        composable("input") {
            InputScreen(onSearchClick = { name ->

                navController.navigate("result/$name")
            })
        }
        composable("result/{name}", arguments = listOf(navArgument("name") { type = NavType.StringType })) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ResultScreen(name = name, info = "Info for $name")
        }
    }
}
