package com.example.nameanalysis.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Defines the theming for the Name Analysis application.
 *
 * This package includes the color palettes, typography, shapes, and the overall theme composable
 * used to apply these styles throughout the application. It supports both light and dark theme modes.
 */

// Color palettes for light and dark themes
private val DarkColorPalette = darkColors(
    primary = Color(0xFFBB86FC),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC5)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC5),
    // Other default colors to override
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF000000),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
)

// Typography configuration for the application
private val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

// Shapes configuration for UI elements
private val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

/**
 * The main theme composable for the Name Analysis application.
 *
 * Applies the defined color palette, typography, and shapes to the content wrapped within this composable.
 * It provides an easy way to switch between light and dark themes.
 *
 * @param darkTheme A boolean flag to toggle between dark and light theme. Defaults to 'false' (light theme).
 * @param content A composable lambda that represents the UI content to which this theme will be applied.
 */
@Composable
fun NameAnalysisTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
