package com.example.scratchcard.domain

import android.view.ViewOverlay
import com.example.scratchcard.data.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

sealed class ScratchCardUseCase {

    class ObserveScratchCardState @Inject constructor(
        private val repository: ScratchCardRepository
    ) : UnitFlowUseCase<ScratchCardModel>() {
        override fun invoke(): Flow<ScratchCardModel> = repository.observeCardState()
    }

    class GetCurrentCardState @Inject constructor(
        private val repository: ScratchCardRepository
    ) : UnitUseCase<ScratchCardModel>() {
        override fun invoke(): ScratchCardModel =
            repository.getCurrentCardState()
    }

    class SetScratched @Inject constructor(
        private val repository: ScratchCardRepository
    ) : UnitUseCase<Unit>() {
        override fun invoke() {
            repository.scratchCard()
        }

    }

    class SetActivated @Inject constructor(
        private val repository: ScratchCardRepository
    ) : UnitUseCase<Unit>() {
        override fun invoke() {
            repository.activateCard()
        }

    }

    class GetVersionCall @Inject constructor(
        private val versionRepository: VersionRepository
    ) : SuspendFlowUseCase<String, Resource<VersionModel?>>() {
        override suspend fun invoke(input: String): Flow<Resource<VersionModel?>> =
            versionRepository.getVersion(input)
    }
}