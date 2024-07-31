package com.example.scratchcard.domain

import com.example.scratchcard.base.*
import com.example.scratchcard.data.ApiResult
import com.example.scratchcard.model.ScratchCardModel
import com.example.scratchcard.model.VersionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

sealed class ScratchCardUseCase {

    class ObserveScratchCardState @Inject constructor(
        private val repository: ScratchCardRepository
    ) : UnitFlowUseCase<ScratchCardModel>() {
        override fun invoke(): Flow<ScratchCardModel> = repository.observeCardState()
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

    class IsCodeValid : UseCase<String, Boolean>() {
        override fun invoke(input: String): Boolean = try {
            val inputNumber = input.toLongOrNull()
            if (inputNumber != null) inputNumber > 277028
            else false
        } catch (e: Exception) {
            false
        }
    }

    class RequestVersion @Inject constructor(
        private val repository: VersionRepository
    ) : SuspendFlowUseCase<String, Unit>() {
        override suspend fun invoke(input: String) {
            repository.requestVersion(input)
        }
    }

    class ObserveVersion @Inject constructor(
        private val repository: VersionRepository
    ) : SuspendUnitFlowUseCase<ApiResult<VersionModel>>() {
        override suspend fun invoke() = repository.observeVersion()
    }
}
