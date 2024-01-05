package com.example.nameanalysis.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A Composable function that creates a card displaying gender information.
 *
 * This function creates a card UI element using Jetpack Compose. The card displays a label and its associated value.
 * It is styled with rounded corners, a light gray background, and padding. The text elements inside the card
 * are styled according to MaterialTheme and have different font sizes and weights.
 *
 * @param label The label text to display at the top of the card. Typically, this represents the category or type of information.
 * @param value The value text to display below the label. This is the actual information or data corresponding to the label.
 */
@Composable
fun GenderInfoCard(label: String, value: String) {
    Card(
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = value,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}