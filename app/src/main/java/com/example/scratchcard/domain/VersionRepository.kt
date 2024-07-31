package com.example.scratchcard.domain

import com.example.scratchcard.data.ApiResult
import com.example.scratchcard.model.VersionModel
import kotlinx.coroutines.flow.Flow

interface VersionRepository {
    suspend fun requestVersion(code: String)

    suspend fun observeVersion(): Flow<ApiResult<VersionModel>>
}
