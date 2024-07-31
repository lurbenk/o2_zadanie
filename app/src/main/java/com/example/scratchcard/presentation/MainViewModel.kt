package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import com.example.scratchcard.domain.ScratchCardModelConverter.toState
import com.example.scratchcard.domain.ScratchCardRepository
import com.example.scratchcard.domain.ScratchCardUseCase.ObserveScratchCardState
import com.example.scratchcard.presentation.MainViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState
) : BaseViewModel<State>(State()) {

    init {
        viewModelScope.launch {
            observeScratchCardState().collect {
                setState(
                    state.value!!.copy(
                        scratchCardState = it.toState()
                    )
                )
            }
        }
    }

    data class State(
        val screenTitle: String = "ScratchCardApplication",
        val scratchCardState: ScratchCardState? = null,
        val scratchButton: ButtonState = ButtonState(text = "Scratch card", action = { } ),
        val activateButton: ButtonState = ButtonState(text = "Activate card", action = { } ),
    )
}