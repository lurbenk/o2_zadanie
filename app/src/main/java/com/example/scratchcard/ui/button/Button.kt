package com.example.scratchcard.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.scratchcard.ui.text.ButtonText

@Composable
fun ScratchButton(
    text: String,
    enabled: Boolean,
    action: () -> Unit,
) {
    Button(onClick = action, enabled = enabled) {
        ButtonText(text = text)
    }
}

@Preview
@Composable
private fun ScratchButtonPreview() {
    Column(Modifier.background(Color.White)) {
        ScratchButton(text = "Test", enabled = true, action = { })
        ScratchButton(text = "Test", enabled = false, action = { })
    }
}
