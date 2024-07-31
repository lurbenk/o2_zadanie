package com.example.scratchcard.data

import com.example.scratchcard.domain.VersionModel
import javax.inject.Inject

interface VersionRemoteDataSource {
    suspend fun getVersion(code: String): Resource<VersionResponseDto>
}

class VersionRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : VersionRemoteDataSource {

    override suspend fun getVersion(code: String): Resource<VersionResponseDto> = apiService.getVersion(code)

}