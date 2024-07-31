package com.example.scratchcard.di

import com.example.scratchcard.data.ScratchCardRepositoryImpl
import com.example.scratchcard.data.VersionRepository
import com.example.scratchcard.domain.ScratchCardRepository
import com.example.scratchcard.domain.ScratchCardUseCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideScratchCardRepository(): ScratchCardRepository {
        return ScratchCardRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetCurrentCardStateUseCase(
        repository: ScratchCardRepository
    ): GetCurrentCardState {
        return GetCurrentCardState(repository)
    }

    @Provides
    @Singleton
    fun provideObserveScratchCardUseCase(
        repository: ScratchCardRepository
    ): ObserveScratchCardState {
        return ObserveScratchCardState(repository)
    }

    @Provides
    @Singleton
    fun provideSetScratchedUseCase(
        repository: ScratchCardRepository
    ): SetScratched {
        return SetScratched(repository)
    }

    @Provides
    @Singleton
    fun provideSetActivatedUseCase(
        repository: ScratchCardRepository
    ): SetActivated {
        return SetActivated(repository)
    }

    @Provides
    @Singleton
    fun provideGetVersionCallUseCase(
        repository: VersionRepository
    ): GetVersionCall {
        return GetVersionCall(repository)
    }
}
