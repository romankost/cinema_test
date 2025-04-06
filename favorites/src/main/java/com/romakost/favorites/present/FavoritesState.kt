package com.romakost.favorites.present

import com.romakost.favorites.data.db.FavoritesEntity

data class FavoritesState(
    val favorites: List<FavoritesEntity> = emptyList(),
    val error: String = ""
) {
    val isNoFavorite = favorites.isEmpty()

    companion object {
        val initState = FavoritesState()
    }
}
