package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import com.example.scratchcard.R
import com.example.scratchcard.R.string
import com.example.scratchcard.base.BaseViewModel
import com.example.scratchcard.base.ViewModelState
import com.example.scratchcard.data.ApiResult
import com.example.scratchcard.domain.ResourceProvider
import com.example.scratchcard.domain.ScratchCardUseCase.*
import com.example.scratchcard.model.ScratchCardModel.Scratched
import com.example.scratchcard.presentation.ActivationViewModel.State
import com.example.scratchcard.presentation.ScratchCardModelConverter.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState,
    private val requestVersion: RequestVersion,
    private val observeVersion: ObserveVersion,
    private val setActivated: SetActivated,
    private val isCodeValid: IsCodeValid,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<State>(State()) {

    init {
        observeCard()
        observeApiCall()
    }

    private fun observeCard() {
        viewModelScope.launch {
            observeScratchCardState().collect {
                state = state.copy(
                    screenTitle = resourceProvider.getString(R.string.button_activate_card),
                    scratchCardState = it.toState(),
                    activateButton = ButtonState(
                        text = resourceProvider.getString(R.string.button_activate_card),
                        action = ::callVersion,
                        enabled = it is Scratched
                    )
                )
            }
        }
    }

    private fun observeApiCall() {
        viewModelScope.launch {
            observeVersion().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        validateResponse(result.data.version)
                    }

                    is ApiResult.Fail -> {
                        setError()
                    }

                    else -> {}
                }
                state = state.copy(
                    loading = if (result is ApiResult.Loading) resourceProvider.getString(string.loading_activating) else null
                )
            }
        }
    }

    private fun callVersion() {
        viewModelScope.launch {
            requestVersion(state.scratchCardState?.code.orEmpty())
        }
    }

    private fun validateResponse(responseValue: String?) {
        if (isCodeValid(responseValue.orEmpty())) setActivated()
        else setError()
    }

    private fun setError() {
        state = state.copy(
            error = ErrorModalState(
                errorText = resourceProvider.getString(string.code_activation_error),
                button = ButtonState(
                    text = resourceProvider.getString(string.button_close),
                    enabled = true,
                    action = ::errorAction
                )
            )
        )
    }

    private fun errorAction() {
        state = state.copy(error = null)
    }

    data class State(
        val screenTitle: String = "",
        val loading: String? = null,
        val scratchCardState: ScratchCardState? = null,
        val error: ErrorModalState? = null,
        val activateButton: ButtonState? = null,
    ) : ViewModelState
}
