package com.example.scratchcard.data

import com.example.scratchcard.domain.ScratchCardRepository
import com.example.scratchcard.model.ScratchCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import javax.inject.Inject

class ScratchCardRepositoryImpl @Inject constructor() : ScratchCardRepository {

    private val cardFlow = MutableStateFlow<ScratchCardModel>(ScratchCardModel.Unscratched)

    override fun observeCardState(): Flow<ScratchCardModel> = cardFlow

    override fun loadCardState(): ScratchCardModel = cardFlow.value

    override fun scratchCard() {
        cardFlow.value = ScratchCardModel.Scratched(generateUUID())
    }

    override fun activateCard() {
        (cardFlow.value as? ScratchCardModel.Scratched)?.code?.let {
            cardFlow.value = ScratchCardModel.Activated(it)
        }
    }

    private fun generateUUID() = UUID.randomUUID().toString().take(8)
}
