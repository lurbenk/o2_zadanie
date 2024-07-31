package com.example.scratchcard.domain

sealed class ScratchCardModel {
    data object Unscratched: ScratchCardModel()
    data class Scratched(
        val code: String
    ): ScratchCardModel()
    data class Activated(
        val code: String
    ): ScratchCardModel()
}
