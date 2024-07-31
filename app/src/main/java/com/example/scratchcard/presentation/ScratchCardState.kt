package com.example.scratchcard.presentation

data class ScratchCardState(
    val scratched: Boolean,
    val code: String?,
    val activated: Boolean,
)
