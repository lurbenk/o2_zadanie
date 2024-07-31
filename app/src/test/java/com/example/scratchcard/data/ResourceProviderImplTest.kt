package com.example.scratchcard.data

import android.content.Context
import com.example.scratchcard.system.ResourceProviderImpl
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourceProviderImplTest {

    @Test
    fun getStringWithValidResIdReturnsCorrectString() {
        val resId = 123
        val expectedString = "Expected String"
        val mockContext: Context = mockk()
        val resourceProvider = ResourceProviderImpl(mockContext)

        every { mockContext.getString(resId) } returns expectedString

        val actualString = resourceProvider.getString(resId)

        assertEquals(expectedString, actualString)

        verify { mockContext.getString(resId) }
    }
}
