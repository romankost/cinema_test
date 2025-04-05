package com.romakost.favorites.present

import com.romakost.favorites.data.db.Favorite

data class FavoritesState (
    val favorites: List<Favorite> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = ""
) {
    val isError = error.isNotEmpty()

    companion object {
        val initState = FavoritesState()
    }
}