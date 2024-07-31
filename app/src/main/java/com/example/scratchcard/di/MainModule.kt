package com.example.scratchcard.di

import android.content.Context
import com.example.scratchcard.data.*
import com.example.scratchcard.domain.*
import com.example.scratchcard.domain.ScratchCardUseCase.*
import com.example.scratchcard.network.*
import com.example.scratchcard.system.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideScratchCardRepository(): ScratchCardRepository {
        return ScratchCardRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideVersionRepository(remoteResource: VersionRemoteResource): VersionRepository {
        return VersionRepositoryImpl(remoteResource)
    }

    @Provides
    @Singleton
    fun provideObserveScratchCardUseCase(repository: ScratchCardRepository): ObserveScratchCardState {
        return ObserveScratchCardState(repository)
    }

    @Provides
    @Singleton
    fun provideSetScratchedUseCase(repository: ScratchCardRepository): SetScratched {
        return SetScratched(repository)
    }

    @Provides
    @Singleton
    fun provideSetActivatedUseCase(repository: ScratchCardRepository): SetActivated {
        return SetActivated(repository)
    }

    @Provides
    @Singleton
    fun provideIsCodeValidUseCase(): IsCodeValid {
        return IsCodeValid()
    }

    @Provides
    @Singleton
    fun provideRequestVersionCallUseCase(repository: VersionRepository): RequestVersion {
        return RequestVersion(repository)
    }

    @Provides
    @Singleton
    fun provideObserveVersionCallUseCase(repository: VersionRepository): ObserveVersion {
        return ObserveVersion(repository)
    }

    @Provides
    @Singleton
    fun provideApiService(): VersionApi {
        return RetrofitClient.versionApi
    }

    @Provides
    @Singleton
    fun provideVersionRemoteResource(versionApi: VersionApi): VersionRemoteResource {
        return VersionRemoteResource(versionApi)
    }
}
