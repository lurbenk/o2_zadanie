package com.example.scratchcard.domain

import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {
    fun observeCardState(): Flow<ScratchCardModel>
    fun getCurrentCardState(): ScratchCardModel
    fun scratchCard()
    fun activateCard()
}