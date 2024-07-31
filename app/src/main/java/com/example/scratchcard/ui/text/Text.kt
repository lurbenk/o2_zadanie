package com.example.scratchcard.ui.text

import android.content.res.Resources.Theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.scratchcard.ui.theme.Typography

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