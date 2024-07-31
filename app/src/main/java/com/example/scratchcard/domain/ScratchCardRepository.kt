package com.example.scratchcard.domain

import com.example.scratchcard.model.ScratchCardModel
import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {
    fun observeCardState(): Flow<ScratchCardModel>
    fun loadCardState(): ScratchCardModel
    fun scratchCard()
    fun activateCard()
}
