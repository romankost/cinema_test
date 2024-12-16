package com.romakost.tv_show

import androidx.compose.runtime.InternalComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.romakost.core.BottomNavigationItem

@OptIn(InternalComposeApi::class)
fun NavGraphBuilder.navigateToFavorites() {
   composable(route = BottomNavigationItem.Favorites.route) {
      FavoritesScreen()
   }
}