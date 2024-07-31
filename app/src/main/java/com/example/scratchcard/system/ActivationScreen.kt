package com.example.scratchcard.system

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scratchcard.presentation.ActivationViewModel
import com.example.scratchcard.presentation.ScratchCardViewModel
import com.example.scratchcard.ui.card.ScratchCard
import com.example.scratchcard.ui.screen.BaseScreen
import com.example.scratchcard.ui.screen.BasicScreenScaffold

@Composable
fun ActivationScreen(navController: NavController, vm: ActivationViewModel = hiltViewModel()) {
    BaseScreen(viewModel = vm) { state ->
        BasicScreenScaffold(
            screenTitle = state.screenTitle,
            isLoading = state.isLoading,
            backAction = { navController.navigate(MAIN_SCREEN)}
        ) {
            state.scratchCardState?.let {
                ScratchCard(state = it)
            }
            state.activateButton?.let {
                Button(onClick = it.action, enabled = it.enabled) {
                    Text(it.text)
                }
            }
        }
    }
}