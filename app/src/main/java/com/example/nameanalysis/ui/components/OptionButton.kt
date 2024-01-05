package com.example.nameanalysis.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A Composable function that creates a styled button with custom text and an onClick action.
 *
 * This function uses Jetpack Compose to create a button UI element. The button is designed to fill the maximum width available,
 * with added vertical padding. The button's colors are based on the MaterialTheme's primary color scheme. The text on the button
 * is customizable and the function allows specifying an action to be performed when the button is clicked.
 *
 * @param text The text to be displayed on the button.
 * @param onClick A lambda function representing the action to be performed when the button is clicked.
 */
@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onPrimary
        )
    }
}