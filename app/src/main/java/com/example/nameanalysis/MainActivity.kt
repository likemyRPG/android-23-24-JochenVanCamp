package com.example.nameanalysis

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.nameanalysis.theme.NameAnalysisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            NameAnalysisTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController, context = this@MainActivity)
                }
            }
        }
    }
}
