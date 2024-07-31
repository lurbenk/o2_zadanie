package com.example.scratchcard.presentation

import androidx.lifecycle.*

open class BaseViewModel<T : Any>(val defaultState: T) : ViewModel() {

    private val _state = MutableLiveData(defaultState)
    val state: LiveData<T> = _state

    protected fun setState(newState: T?) {
        newState?.let {
            _state.value = newState
        }
    }
}

