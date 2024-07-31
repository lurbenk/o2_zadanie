package com.example.scratchcard.presentation

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scratchcard.data.Status.*
import com.example.scratchcard.domain.ScratchCardModel.Scratched
import com.example.scratchcard.domain.ScratchCardModelConverter.toState
import com.example.scratchcard.domain.ScratchCardUseCase.*
import com.example.scratchcard.presentation.ActivationViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val observeScratchCardState: ObserveScratchCardState,
    private val getVersionCall: GetVersionCall,
    private val setActivated: SetActivated
) : BaseViewModel<State>(State()) {

    init {
        viewModelScope.launch {
            observeScratchCardState().collect {
                setState(
                    state.value?.copy(
                        scratchCardState = it.toState(),
                        activateButton = ButtonState(text = "Activate Card", action = ::callVersion, enabled = it is Scratched)
                    )
                )
            }
        }
    }

    private fun callVersion() {
        viewModelScope.launch {
            getVersionCall(state.value?.scratchCardState?.code ?: "").collect {
                when (it.status) {
                    SUCCESS -> {
                        setState(state.value?.copy(isLoading = false))
                        validateResponse(it.data?.version)
                    }
                    ERROR -> {
                        setState(state.value?.copy(isLoading = false))
                        setError()
                    }
                    LOADING -> setState(state.value?.copy(isLoading = true))
                }
            }
        }
    }

    private fun validateResponse(responseValue: String?) {
        if (ResponseValidator.isValid(responseValue)) setActivated()
        else setError()
    }

    private fun setError() {
            setState(
                state.value?.copy(
                    error = ErrorModalState(
                        errorText = "Code Activation Error",
                        errorAction = ::errorAction
                    )
                )
            )
    }

    private fun errorAction() {
            setState(
                state.value?.copy(
                    error = null
                )
            )
    }

    data class State(
        val screenTitle: String = "ActivateCard",
        val isLoading: Boolean = false,
        val scratchCardState: ScratchCardState? = null,
        val error: ErrorModalState? = null,
        val activateButton: ButtonState? = null,
    )
}
