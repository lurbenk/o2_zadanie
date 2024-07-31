package com.example.scratchcard.data

import com.example.scratchcard.domain.VersionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class VersionRepository @Inject constructor(
    private val versionRemoteDataSource: VersionRemoteDataSource
) {
    suspend fun getVersion(code: String): Flow<Resource<VersionModel?>> =
         flow {
            emit(Resource.loading(null))
            try {
                emit(Resource.success(data = versionRemoteDataSource.getVersion(code).data?.toDomain()))
            } catch (e: Exception) {
                emit(Resource.error(null, message = e.message ?: "Error"))
            }
        }.flowOn(Dispatchers.IO)
}

private fun VersionResponseDto.toDomain(): VersionModel {
    return VersionModel(
        version = this.android
    )
}
