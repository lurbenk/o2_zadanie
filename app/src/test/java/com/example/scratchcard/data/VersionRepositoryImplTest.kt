package com.example.scratchcard.data

import com.example.scratchcard.data.ApiResult.Success
import com.example.scratchcard.model.VersionModel
import com.example.scratchcard.network.VersionRemoteResource
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class VersionRepositoryImplTest {

    @Test
    fun testRequestVersion() = runBlocking {
        val remoteResource = remoteResource()
        val repository = VersionRepositoryImpl(remoteResource)
        val code = "versionCode"
        coEvery { remoteResource.request(code) } just Runs

        repository.requestVersion(code)

        coVerify { remoteResource.request(code) }
    }

    @Test
    fun testObserveVersion() = runTest {
        val remoteResource = remoteResource()
        val repository = VersionRepositoryImpl(remoteResource)
        val flow: Flow<ApiResult<VersionModel>> = flow { emit(Success(VersionModel("1.0"))) }
        every { remoteResource.observe() } returns flow

        val result = repository.observeVersion()

        assertEquals(flow, result)
        verify { remoteResource.observe() }
    }

    private fun remoteResource(): VersionRemoteResource = mockk()
}
