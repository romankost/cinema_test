package com.romakost.test_compose

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar =  { BottomNavigation(navController = navController) }
    ) {
        AppNavigation(navController = navController)
    }
}