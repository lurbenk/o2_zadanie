package com.example.scratchcard.presentation

data class ButtonState(
    val text: String,
    val enabled: Boolean = true,
    val action: () -> Unit,
)
