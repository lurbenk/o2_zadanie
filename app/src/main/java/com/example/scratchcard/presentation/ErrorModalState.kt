package com.example.scratchcard.presentation

data class ErrorModalState(
    val errorText: String,
    val errorAction: () -> Unit
)
