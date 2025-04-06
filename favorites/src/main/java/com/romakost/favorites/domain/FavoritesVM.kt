package com.romakost.favorites.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romakost.favorites.data.FavoriteMovieRepo
import com.romakost.favorites.data.db.FavoritesEntity
import com.romakost.favorites.present.FavoritesEvent
import com.romakost.favorites.present.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

@HiltViewModel
class FavoritesVM @Inject constructor(
    private val favoritesRepo: FavoriteMovieRepo
) : ViewModel() {
    private val state = MutableStateFlow(FavoritesState.initState)
    val favoritesScreenState = state.asStateFlow()

    init {
        viewModelScope.launch {
            favoritesRepo.getAllFavorites().collect {
                pushFavoritesState(favorites = it)
            }
        }
    }

    fun event(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.OnMovieItemClicked -> handleDeleteMovieFromFavorites(event.favoriteData)
            is FavoritesEvent.ClearAll -> handleClearAll()
        }
    }

    private fun pushFavoritesState(favorites: List<FavoritesEntity>) {
        state.tryEmit(state.value.copy(favorites = favorites))
    }

    private fun handleClearAll() {
        viewModelScope.launch {
            favoritesRepo.deleteAllFavorites().collect {
                pushFavoritesState(emptyList())
            }
        }
    }

    private fun handleDeleteMovieFromFavorites(favorite: FavoritesEntity) {
        viewModelScope.launch {
            favoritesRepo.deleteFavoritesByMovieName(favorite.movieName)
        }
    }
}
