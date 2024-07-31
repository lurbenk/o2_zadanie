package com.example.scratchcard.network

import com.example.scratchcard.network.VersionResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VersionApi {
    @GET("version")
    suspend fun getVersion(@Query("code") code: String): VersionResponseDto
}
