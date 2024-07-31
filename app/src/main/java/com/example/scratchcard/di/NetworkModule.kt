package com.example.scratchcard.di

import com.example.scratchcard.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideVersionRemoteDataSource(apiService: ApiService): VersionRemoteDataSource {
        return VersionRemoteDataSourceImpl(apiService)
    }
}
