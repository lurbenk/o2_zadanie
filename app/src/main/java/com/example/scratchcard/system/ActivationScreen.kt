package com.example.scratchcard.system

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scratchcard.base.BaseScreen
import com.example.scratchcard.presentation.ActivationViewModel
import com.example.scratchcard.ui.VerticalSpace
import com.example.scratchcard.ui.button.ScratchButton
import com.example.scratchcard.ui.card.ScratchCard
import com.example.scratchcard.ui.modal.BottomDialog
import com.example.scratchcard.ui.screen.BasicScreenScaffold

@Composable
fun ActivationScreen(navController: NavController, vm: ActivationViewModel = hiltViewModel()) {
    BaseScreen(viewModel = vm) { state ->
        BasicScreenScaffold(
            screenTitle = state.screenTitle,
            loading = state.loading,
            backAction = { navController.navigate(MAIN_SCREEN) }
        ) {
            state.error?.let { BottomDialog(text = it.errorText, closeButton = it.button) }
            state.scratchCardState?.let { ScratchCard(state = it) }
            VerticalSpace()
            state.activateButton?.let { buttonState ->
                ScratchButton(text = buttonState.text, enabled = buttonState.enabled, action = buttonState.action)
            }
        }
    }
}
