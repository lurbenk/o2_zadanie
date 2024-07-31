package com.example.scratchcard.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scratchcard.base.BaseScreen
import com.example.scratchcard.presentation.ScratchCardViewModel
import com.example.scratchcard.ui.VerticalSpace
import com.example.scratchcard.ui.button.ScratchButton
import com.example.scratchcard.ui.card.ScratchCard
import com.example.scratchcard.ui.screen.BasicScreenScaffold

@Composable
fun ScratchCardScreen(navController: NavController, vm: ScratchCardViewModel = hiltViewModel()) {
    BaseScreen(viewModel = vm) { state ->
        DisposableEffect(Unit) {
            onDispose {
                vm.cancelCoroutines()
            }
        }

        BasicScreenScaffold(
            screenTitle = state.screenTitle,
            loading = state.loading,
            backAction = { navController.navigate(MAIN_SCREEN) }
        ) {
            state.scratchCardState?.let { ScratchCard(state = it) }
            VerticalSpace()
            state.scratchButton?.let { buttonState ->
                ScratchButton(text = buttonState.text, enabled = buttonState.enabled, action = buttonState.action)
            }
        }
    }
}
