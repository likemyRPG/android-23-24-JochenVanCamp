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

/**
 * A test suite for verifying navigation flows in the Name Analysis application.
 *
 * This class contains UI tests for the application using Jetpack Compose testing tools. It tests the navigation
 * between different screens and verifies if the correct UI elements are displayed upon navigation. The tests
 * use a TestNavHostController to simulate navigation events and observe UI changes.
 */
class NavigationTest {

    /**
     * Rule to set up a Compose testing environment.
     *
     * This rule provides a Compose testing context and allows for setting up and manipulating Compose UI content.
     */
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    /**
     * Sets up the testing environment before each test.
     *
     * This method initializes the test navigation controller and sets the Compose content for testing.
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            // Initialization of TestNavHostController and AppNavHost
            val context = LocalContext.current
            val appContainer = AppDataContainer(context)
            navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            AppNavHost(navController = navController, appContainer = appContainer)
        }
    }

    /**
     * Verifies that the Home screen is displayed correctly.
     *
     * This test checks if the "Start" button is visible on the Home screen.
     */
    @Test
    fun verifyHomeScreen() {
        // UI assertions for the Home screen
        composeTestRule
            .onNodeWithText("Start")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation from the Home screen to the Options menu.
     *
     * This test simulates a click on the "Start" button and verifies if the Options menu is displayed.
     */
    @Test
    fun navigateToOptionMenu() {
        // Navigation and UI assertions for the Options menu
        composeTestRule
            .onNodeWithText("Start")
            .performClick()
        composeTestRule
            .onNodeWithText("Options")
            .assertIsDisplayed()
    }

    /**
     * Tests navigation from the Options menu to the Input screen.
     *
     * This test simulates navigation through the UI and verifies if the Input screen is displayed correctly.
     */
    @Test
    fun navigateToInputScreen() {
        // Navigation and UI assertions for the Input screen
        navigateToOptionMenu()

        composeTestRule
            .onNodeWithText("Gender Analysis")
            .performClick()

        composeTestRule
            .onNodeWithText("Enter a Name")
            .assertIsDisplayed()
    }

    /**
     * Tests the navigation flow from the Input screen to the Result screen.
     *
     * This test enters a name in the Input screen, submits it for analysis, and verifies if the Result screen
     * is displayed with the correct information.
     */
    @Test
    fun inputScreenToResultScreenNavigation() {
        // Navigation, input actions, and UI assertions for the Result screen
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