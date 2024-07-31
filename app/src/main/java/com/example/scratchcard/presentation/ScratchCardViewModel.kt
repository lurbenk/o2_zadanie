package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import com.example.scratchcard.domain.ScratchCardModel.Scratched
import com.example.scratchcard.domain.ScratchCardModel.Unscratched
import com.example.scratchcard.domain.ScratchCardModelConverter.toState
import com.example.scratchcard.domain.ScratchCardUseCase
import com.example.scratchcard.domain.ScratchCardUseCase.ObserveScratchCardState
import com.example.scratchcard.domain.ScratchCardUseCase.SetScratched
import com.example.scratchcard.presentation.ScratchCardViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScratchCardViewModel
@Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState,
    private val setScratched: SetScratched
) : BaseViewModel<State>(State()) {

    init {
        viewModelScope.launch {
            observeScratchCardState().collect {
                setState(
                    state.value!!.copy(
                        scratchCardState = it.toState(),
                        scratchButton = ButtonState(text = "Scratch card", action = ::scratchMe, enabled = it is Unscratched )
                    )
                )
            }
        }
    }

    private fun scratchMe() {
        viewModelScope.launch {
            setState(state.value!!.copy(
                isLoading = true
            ))
            delay(5000)
            setState(state.value!!.copy(
                isLoading = false
            ))
            setScratched()
        }

    }

    data class State(
        val screenTitle: String = "ScratchCard",
        val isLoading: Boolean = false,
        val scratchCardState: ScratchCardState? = null,
        val scratchButton: ButtonState? = null,
    )
}
