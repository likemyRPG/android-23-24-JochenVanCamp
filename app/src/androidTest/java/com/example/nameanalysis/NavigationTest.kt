package com.example.nameanalysis

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.nameanalysis.data.AppDataContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val appContainer = AppDataContainer(context)
            navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            AppNavHost(navController = navController, appContainer = appContainer)
        }
    }

    @Test
    fun verifyHomeScreen() {
        composeTestRule
            .onNodeWithText("Start")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToOptionMenu() {
        composeTestRule
            .onNodeWithText("Start")
            .performClick()
        composeTestRule
            .onNodeWithText("Options")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToInputScreen() {
        navigateToOptionMenu()

        composeTestRule
            .onNodeWithText("Gender Analysis")
            .performClick()

        composeTestRule
            .onNodeWithText("Enter a Name")
            .assertIsDisplayed()
    }

    @Test
    fun inputScreenToResultScreenNavigation() {
        navigateToInputScreen()

        val testName = "Alice"
        composeTestRule
            .onNodeWithText("Enter a Name")
            .performTextInput(testName)

        composeTestRule
            .onNodeWithText("Analyze Gender")
            .performClick()

        Thread.sleep(5000)

        composeTestRule
            .onNodeWithText("Name")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(testName)
            .assertIsDisplayed()
    }
}