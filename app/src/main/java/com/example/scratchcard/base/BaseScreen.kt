package com.example.scratchcard.base

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun <T : ViewModelState> BaseScreen(
    viewModel: BaseViewModel<T> = hiltViewModel(),
    content: @Composable (T) -> Unit
) {
    val state by viewModel.states.collectAsState(viewModel.defaultState)
    content(state)
}
