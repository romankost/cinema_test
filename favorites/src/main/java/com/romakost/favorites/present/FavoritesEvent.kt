package com.romakost.favorites.present

import com.romakost.favorites.data.db.Favorite

sealed interface FavoritesEvent {
    data class OnMovieItemClicked(val favoriteData: Favorite): FavoritesEvent
    data object ClearAll:  FavoritesEvent
}