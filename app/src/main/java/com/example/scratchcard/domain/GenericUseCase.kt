package com.example.scratchcard.domain

import kotlinx.coroutines.flow.Flow

abstract class SuspendFlowUseCase<in I: Any, out O : Any> {
    abstract suspend operator fun invoke(input: I): Flow<O>
}

abstract class UnitFlowUseCase<out O : Any> {
    abstract operator fun invoke(): Flow<O>
}

abstract class UnitUseCase<out O : Any> {
    abstract operator fun invoke(): O
}