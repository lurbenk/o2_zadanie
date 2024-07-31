package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import com.example.scratchcard.R.string
import com.example.scratchcard.base.BaseViewModel
import com.example.scratchcard.base.ViewModelState
import com.example.scratchcard.domain.ResourceProvider
import com.example.scratchcard.domain.ScratchCardUseCase.ObserveScratchCardState
import com.example.scratchcard.presentation.MainViewModel.State
import com.example.scratchcard.presentation.ScratchCardModelConverter.toState
import com.example.scratchcard.system.ACTIVATION_SCREEN
import com.example.scratchcard.system.SCRATCH_CARD_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<State>(State()) {

    init {
        viewModelScope.launch {
            observeScratchCardState().collect {
                state = state.copy(
                    screenTitle = resourceProvider.getString(string.screen_title_main),
                    scratchCardState = it.toState(),
                    scratchButton = NavigationButtonState(
                        text = resourceProvider.getString(string.button_scratch_card),
                        destination = SCRATCH_CARD_SCREEN
                    ),
                    activateButton = NavigationButtonState(
                        text = resourceProvider.getString(string.button_activate_card),
                        destination = ACTIVATION_SCREEN
                    )
                )
            }
        }
    }

    data class State(
        val screenTitle: String = "",
        val scratchCardState: ScratchCardState? = null,
        val activateButtonEnabled: Boolean = false,
        val scratchButton: NavigationButtonState? = null,
        val activateButton: NavigationButtonState? = null,
    ) : ViewModelState
}
