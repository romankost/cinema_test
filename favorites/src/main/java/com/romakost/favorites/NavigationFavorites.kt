package com.romakost.favorites

import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.romakost.core.BottomNavigationItem
import com.romakost.favorites.domain.FavoritesVM
import com.romakost.favorites.present.screen.FavoritesScreen

@OptIn(InternalComposeApi::class)
fun NavGraphBuilder.navigateToFavorites() {
    composable(route = BottomNavigationItem.Favorites.route) {
        val vm = hiltViewModel<FavoritesVM>()
        val state by vm.favoritesScreenState.collectAsStateWithLifecycle()

        FavoritesScreen(
            state = state,
            onEvent = vm::event
        )
    }
}
