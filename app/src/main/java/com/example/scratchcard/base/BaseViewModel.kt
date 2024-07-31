package com.example.scratchcard.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<T : ViewModelState>(val defaultState: T) : ViewModel() {

    private val statesFlow = MutableStateFlow(defaultState)
    var state: T
        get() = statesFlow.value
        set(value) {
            statesFlow.value = value
        }

    val states: StateFlow<T> = statesFlow
}

interface ViewModelState
