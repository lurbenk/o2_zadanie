package com.example.scratchcard.data

import com.example.scratchcard.domain.VersionRepository
import com.example.scratchcard.model.VersionModel
import com.example.scratchcard.network.VersionRemoteResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VersionRepositoryImpl @Inject constructor(
    private val remoteResource: VersionRemoteResource
) : VersionRepository {

    override suspend fun requestVersion(code: String) {
        remoteResource.request(code)
    }

    override suspend fun observeVersion(): Flow<ApiResult<VersionModel>> = remoteResource.observe()
}
