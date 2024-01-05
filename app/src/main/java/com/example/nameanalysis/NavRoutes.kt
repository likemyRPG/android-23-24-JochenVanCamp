package com.example.nameanalysis

/**
 * Object containing the navigation route constants for the Name Analysis application.
 *
 * This object defines constants for each navigation route used in the application's navigation graph.
 * These constants are used to identify and navigate to different screens within the application.
 */
object NavRoutes {
    /**
     * The route for the home screen.
     */
    const val HOME = "home"
    /**
     * The route for the options menu screen.
     */
    const val OPTIONS = "options"
    /**
     * The route for the input screen, where users enter the name for analysis.
     */
    const val INPUT = "input"
    /**
     * The route for the gender result screen.
     *
     * This route includes placeholders for passing arguments such as name, gender, probability, and count.
     */
    const val GENDER_RESULT = "genderResult/{name}/{gender}/{probability}/{count}"
}
