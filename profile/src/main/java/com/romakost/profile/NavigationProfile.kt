package com.romakost.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.romakost.core.BottomNavigationItem

fun NavGraphBuilder.navigateToProfile() {
   composable(route = BottomNavigationItem.Profile.route) {
      ProfileScreen()
   }
}