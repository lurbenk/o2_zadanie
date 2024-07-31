package com.example.scratchcard.system

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scratchcard.presentation.MainViewModel
import com.example.scratchcard.ui.card.ScratchCard
import com.example.scratchcard.ui.screen.BaseScreen
import com.example.scratchcard.ui.screen.BasicScreenScaffold

@Composable
fun MainScreen(navController: NavController, vm: MainViewModel = hiltViewModel()) {
    BaseScreen(viewModel = vm) { state ->
        BasicScreenScaffold(
            screenTitle = state.screenTitle,
            isLoading = false,
            backAction = null
        ) {
            state.scratchCardState?.let {
                ScratchCard(state = it)
            }

            Button(onClick = { navController.navigate(SCRATCH_CARD_SCREEN)}) {
                Text("ScratchCard")
            }
            Button(onClick = { navController.navigate(ACTIVATION_SCREEN) }) {
                Text("Activation")
            }
        }
    }
}
