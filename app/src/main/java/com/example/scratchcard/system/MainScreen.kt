package com.example.scratchcard.system

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scratchcard.base.BaseScreen
import com.example.scratchcard.presentation.MainViewModel
import com.example.scratchcard.ui.VerticalSpace
import com.example.scratchcard.ui.button.ScratchButton
import com.example.scratchcard.ui.card.ScratchCard
import com.example.scratchcard.ui.screen.BasicScreenScaffold

@Composable
fun MainScreen(navController: NavController, vm: MainViewModel = hiltViewModel()) {
    BaseScreen(viewModel = vm) { state ->
        BasicScreenScaffold(screenTitle = state.screenTitle) {
            state.scratchCardState?.let { ScratchCard(state = it) }
            VerticalSpace()
            state.scratchButton?.let { buttonState ->
                ScratchButton(text = buttonState.text, enabled = true, action = { navController.navigate(buttonState.destination) })
            }
            VerticalSpace()
            state.activateButton?.let { buttonState ->
                ScratchButton(text = buttonState.text, enabled = true, action = { navController.navigate(buttonState.destination) })
            }
        }
    }
}
