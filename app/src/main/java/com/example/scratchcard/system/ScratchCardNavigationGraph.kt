package com.example.scratchcard.system

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun ScratchCardNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MAIN_SCREEN) {
        composable(MAIN_SCREEN) {
            MainScreen(navController)
        }
        composable(SCRATCH_CARD_SCREEN) {
            ScratchCardScreen(navController)
        }
        composable(ACTIVATION_SCREEN) {
            ActivationScreen(navController)
        }
    }
}

const val MAIN_SCREEN = "main"
const val SCRATCH_CARD_SCREEN = "scratchCard"
const val ACTIVATION_SCREEN = "activation"