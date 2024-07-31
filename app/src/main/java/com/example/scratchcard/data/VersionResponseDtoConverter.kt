package com.example.scratchcard.data

import com.example.scratchcard.model.VersionModel
import com.example.scratchcard.network.VersionResponseDto

object VersionResponseDtoConverter {
    fun toDomain(external: VersionResponseDto): VersionModel {
        return VersionModel(
            version = external.android
        )
    }
}
