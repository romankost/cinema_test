package com.romakost.favorites.present

import com.romakost.favorites.data.db.FavoritesEntity

sealed interface FavoritesEvent {
    data class OnMovieItemClicked(val favoriteData: FavoritesEntity) : FavoritesEvent
    data object ClearAll : FavoritesEvent
}
