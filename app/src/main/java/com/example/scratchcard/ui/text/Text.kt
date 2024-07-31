package com.example.scratchcard.ui.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ButtonText(text: String) {
    Text(
        style = MaterialTheme.typography.bodyLarge,
        text = text,
        color = MaterialTheme.colorScheme.background,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun LoadingText(text: String) {
    Text(
        style = MaterialTheme.typography.bodyLarge,
        text = text,
        color = Color.White,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun RegularText(text: String) {
    Text(
        style = MaterialTheme.typography.bodyLarge,
        text = text,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun LargeText(text: String) {
    Text(
        style = MaterialTheme.typography.headlineLarge,
        text = text,
        color = MaterialTheme.colorScheme.error,
    )
}
