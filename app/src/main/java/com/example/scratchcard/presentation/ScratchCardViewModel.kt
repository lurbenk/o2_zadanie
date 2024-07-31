package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import com.example.scratchcard.R
import com.example.scratchcard.R.string
import com.example.scratchcard.base.BaseViewModel
import com.example.scratchcard.base.ViewModelState
import com.example.scratchcard.domain.ResourceProvider
import com.example.scratchcard.domain.ScratchCardUseCase.ObserveScratchCardState
import com.example.scratchcard.domain.ScratchCardUseCase.SetScratched
import com.example.scratchcard.model.ScratchCardModel.Unscratched
import com.example.scratchcard.presentation.ScratchCardModelConverter.toState
import com.example.scratchcard.presentation.ScratchCardViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ScratchCardViewModel
@Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState,
    private val setScratched: SetScratched,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<State>(State()) {

    init {
        viewModelScope.launch {
            observeScratchCardState().collect {
                state = state.copy(
                    screenTitle = resourceProvider.getString(R.string.button_scratch_card),
                    scratchCardState = it.toState(),
                    scratchButton = ButtonState(
                        text = resourceProvider.getString(R.string.button_scratch_card),
                        action = ::scratchMe,
                        enabled = it is Unscratched
                    )
                )
            }
        }
    }

    private fun scratchMe() {
        viewModelScope.launch {
            state = state.copy(loading = resourceProvider.getString(string.loading_scratching))
            delay(2000)
            state = state.copy(loading = null)
            setScratched()
        }
    }

    public fun cancelCoroutines() {
        viewModelScope.cancel()
    }

    data class State(
        val screenTitle: String = "",
        val loading: String? = null,
        val scratchCardState: ScratchCardState? = null,
        val scratchButton: ButtonState? = null,
    ) : ViewModelState
}
