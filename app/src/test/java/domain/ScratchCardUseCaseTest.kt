package domain

import com.example.scratchcard.data.ApiResult
import com.example.scratchcard.domain.ScratchCardRepository
import com.example.scratchcard.domain.ScratchCardUseCase.*
import com.example.scratchcard.domain.VersionRepository
import com.example.scratchcard.model.ScratchCardModel
import com.example.scratchcard.model.VersionModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ScratchCardUseCaseTest {

    @Test
    fun testObserveScratchCardState() = runTest {
        val scratchCardRepository = scratchCardRepository()
        val scratchCardModel = ScratchCardModel.Activated("code")
        val flow: Flow<ScratchCardModel> = flow { emit(scratchCardModel) }
        every { scratchCardRepository.observeCardState() } returns flow

        val observeScratchCardState = ObserveScratchCardState(scratchCardRepository)

        val resultFlow = observeScratchCardState.invoke()
        val result = resultFlow.toList()

        assertEquals(listOf(scratchCardModel), result)
        verify { scratchCardRepository.observeCardState() }
    }

    @Test
    fun testSetScratched() = runTest {
        val scratchCardRepository = scratchCardRepository()
        val setScratched = SetScratched(scratchCardRepository)

        setScratched.invoke()

        verify { scratchCardRepository.scratchCard() }
    }

    @Test
    fun testSetActivated() = runTest {
        val scratchCardRepository = scratchCardRepository()
        val setActivated = SetActivated(scratchCardRepository)

        setActivated.invoke()

        verify { scratchCardRepository.activateCard() }
    }

    @Test
    fun testIsCodeValid() {
        val isCodeValid = IsCodeValid()

        assertEquals(true, isCodeValid.invoke("277029"))
        assertEquals(false, isCodeValid.invoke("277027"))
        assertEquals(false, isCodeValid.invoke("invalid"))
    }

    @Test
    fun testRequestVersion() = runTest {
        val code = "versionCode"
        val versionRepository = versionRepository()

        coEvery { versionRepository.requestVersion(code) } returns Unit

        val requestVersion = RequestVersion(versionRepository)

        requestVersion.invoke(code)

        coVerify { versionRepository.requestVersion(code) }
    }

    @Test
    fun testObserveVersion() = runTest {
        val versionModel = VersionModel("1.0")
        val versionRepository = versionRepository()
        val apiResult = ApiResult.Success(versionModel)
        val flow: Flow<ApiResult<VersionModel>> = flow { emit(apiResult) }
        coEvery { versionRepository.observeVersion() } returns flow

        val observeVersion = ObserveVersion(versionRepository)

        val resultFlow = observeVersion.invoke()
        val result = resultFlow.toList()

        assertEquals(listOf(apiResult), result)
        coVerify { versionRepository.observeVersion() }
    }

    private fun scratchCardRepository() = mockk<ScratchCardRepository>(relaxed = true)
    private fun versionRepository() = mockk<VersionRepository>(relaxed = true)
}