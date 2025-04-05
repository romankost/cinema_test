package com.romakost.trend_movie.movie_list.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romakost.core.ResourceManager
import com.romakost.core.network.NetworkResult
import com.romakost.favorites.data.FavoriteMovieRepo
import com.romakost.favorites.data.db.Favorite
import com.romakost.trend_movie.R
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_list.data.TrendingMovieData
import com.romakost.trend_movie.movie_list.data.network.MovieRepoImpl
import com.romakost.trend_movie.movie_list.data.toMovieItemViewState
import com.romakost.trend_movie.movie_list.present.MovieItemViewState
import com.romakost.trend_movie.movie_list.present.MovieListEffect
import com.romakost.trend_movie.movie_list.present.MovieListEvent
import com.romakost.trend_movie.movie_list.present.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListVM
@Inject constructor(
    private val movieRepo: MovieRepoImpl,
    private val resourceManager: ResourceManager,
    private val favoritesRepo: FavoriteMovieRepo
) : ViewModel() {
    private val state = MutableStateFlow(MovieListState.initState)
    val movieListScreenState = state.asStateFlow()

    private val effect = Channel<MovieListEffect>(Channel.BUFFERED)
    val screenEffect = effect.receiveAsFlow()

    init {
        fetchData()
    }

    fun event(event: MovieListEvent) {
        when(event) {
            is MovieListEvent.OnMovieItemClicked -> handleMovieItemClick(event.movieData)
            is MovieListEvent.AddOnFavorites -> manageFavorites(event.movieData)
        }
    }

    private fun handleMovieItemClick(movieData: MovieItemViewState) {
        viewModelScope.launch {
            effect.send(
                MovieListEffect.NavigateToShowDetail(
                    MovieDetailsArgs(
                        name = movieData.title,
                        description = movieData.overview,
                        posterUrl = movieData.posterPath,
                        voteAverage = movieData.voteAverage,
                        releaseDate = movieData.releaseDate
                    )
                )
            )
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            favoritesRepo.getAllFavorites().combine(movieRepo.fetchMovie()) { favorites, moviesResponse ->
                when(moviesResponse) {
                    is NetworkResult.Error -> pushNewErrorState(resourceManager.getString(R.string.error_message))
                    is NetworkResult.Exception -> pushNewErrorState(resourceManager.getString(R.string.exception_message))
                    is NetworkResult.Success -> mergeFavoritesAndPushContent(moviesList = moviesResponse.data.results, favorites = favorites)
                }
            }.collect()
        }
    }

    private fun manageFavorites(movieData: MovieItemViewState) {
        viewModelScope.launch {
            favoritesRepo.isFavoritesExist(movieData.title).collect { isExist ->
                if (isExist) {
                    removeFromFavorites(movieData.title)
                } else {
                    addToFavorites(movieData)
                }
                updateFavoritesIcon(movieData)
            }
        }
    }

    private fun mergeFavoritesAndPushContent(moviesList: List<TrendingMovieData>, favorites: List<Favorite>) {
        val movieAndFavorites = moviesList.map { movie -> movie.toMovieItemViewState(favorites.any { it.movieName == movie.title}) }
        pushNewContentState(movieAndFavorites)
    }

    private fun pushNewErrorState(error: String) {
        state.tryEmit(state.value.copy(isLoading = false, error = error))
    }

    private fun pushNewContentState(movieList: List<MovieItemViewState>) {
        state.tryEmit(state.value.copy(isLoading = false, trendMovieList = movieList))
    }

    private fun updateFavoritesIcon(movieData: MovieItemViewState) {
        with(state.value) {
            val index = trendMovieList.indexOfFirst { it.title == movieData.title }
            if (index != -1) {
                val updatedList = trendMovieList.toMutableList()
                    .apply { this[index] = this[index].copy(isLiked = !movieData.isLiked) }
                pushNewContentState(updatedList)
            }
        }
    }

    private suspend fun  removeFromFavorites(title: String) {
        favoritesRepo.deleteFavoritesByMovieName(movieName = title)
    }

    private suspend fun addToFavorites(movie: MovieItemViewState) {
        favoritesRepo.insertFavorite(
            Favorite(
                movieName = movie.title,
                posterPath = movie.posterPath
            )
        )
    }
}