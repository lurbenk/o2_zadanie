package com.example.scratchcard.network

import com.example.scratchcard.data.VersionResponseDtoConverter
import com.example.scratchcard.model.VersionModel
import javax.inject.Inject

class VersionRemoteResource @Inject constructor(
    private val versionApi: VersionApi
) : NetworkObserver<String, VersionResponseDto, VersionModel>(
    apiCall = versionApi::getVersion,
    converter = VersionResponseDtoConverter::toDomain
)
