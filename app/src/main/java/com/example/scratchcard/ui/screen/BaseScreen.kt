package com.example.scratchcard.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scratchcard.presentation.BaseViewModel

@Composable
fun <T : Any> BaseScreen(
    viewModel: BaseViewModel<T> = hiltViewModel(),
    content: @Composable (T) -> Unit
) {
    val state by viewModel.state.observeAsState(viewModel.defaultState)
    content(state)
}