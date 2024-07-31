package com.example.scratchcard.data

import com.example.scratchcard.model.ScratchCardModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ScratchCardRepositoryImplTest {

    @Test
    fun testInitialState() = runTest {
        val repository = repository()

        val initialState = repository.observeCardState().first()
        assertEquals(ScratchCardModel.Unscratched, initialState)
    }

    @Test
    fun testScratchCard() = runTest {
        val repository = repository()

        repository.scratchCard()

        val stateAfterScratch = repository.observeCardState().first()
        assert(stateAfterScratch is ScratchCardModel.Scratched)
    }

    @Test
    fun testActivateCard() = runTest {
        val repository = repository()

        repository.scratchCard()
        repository.activateCard()

        val stateAfterActivation = repository.observeCardState().first()
        val scratchedState = stateAfterActivation as? ScratchCardModel.Activated
        assert(scratchedState != null)
        assertEquals((stateAfterActivation as? ScratchCardModel.Activated)?.code, scratchedState?.code)
    }

    @Test
    fun testActivateCardWithoutScratching() = runTest {
        val repository = repository()

        repository.activateCard()

        val stateAfterActivationAttempt = repository.observeCardState().first()
        assertEquals(ScratchCardModel.Unscratched, stateAfterActivationAttempt)
    }

    private fun repository() = ScratchCardRepositoryImpl()
}