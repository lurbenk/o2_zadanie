package com.example.scratchcard.ui.modal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.scratchcard.presentation.ButtonState
import com.example.scratchcard.ui.VerticalSpace
import com.example.scratchcard.ui.button.ScratchButton
import com.example.scratchcard.ui.text.RegularText

@Composable
fun BottomDialog(
    text: String,
    closeButton: ButtonState,
) {
    Dialog(
        onDismissRequest = closeButton.action,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            Card(shape = MaterialTheme.shapes.small) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    RegularText(text = text)
                    VerticalSpace()
                    ScratchButton(text = closeButton.text, enabled = closeButton.enabled, action = closeButton.action)
                }
            }
        }
    }
}
