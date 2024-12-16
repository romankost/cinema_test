package com.romakost.trend_movie.movie_list.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romakost.core.ResourceManager
import com.romakost.core.network.NetworkResult
import com.romakost.trend_movie.R
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_list.data.MovieData
import com.romakost.trend_movie.movie_list.data.TrendingMovieData
import com.romakost.trend_movie.movie_list.data.TrendingMovieResponse
import com.romakost.trend_movie.movie_list.data.network.CinemaRepoImpl
import com.romakost.trend_movie.movie_list.data.toMovieData
import com.romakost.trend_movie.movie_list.present.MovieListEffect
import com.romakost.trend_movie.movie_list.present.MovieListEvent
import com.romakost.trend_movie.movie_list.present.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListVM
@Inject constructor(
    private val repo: CinemaRepoImpl,
    private val resourceManager: ResourceManager
) : ViewModel() {
    private val state = MutableStateFlow(MovieListState.initState)
    val movieListScreenState = state.asStateFlow()

    private val effect = Channel<MovieListEffect>(Channel.BUFFERED)
    val screenEffect = effect.receiveAsFlow()

    init {
        fetchMovie()
    }

    fun event(event: MovieListEvent) {
        when(event) {
            is MovieListEvent.OnMovieItemClicked -> handleMovieItemClick(event.movieData)
        }
    }

    private fun fetchMovie() {
        viewModelScope.launch {
            repo.fetchMovie().collect {
                parseResponse(it)
            }
        }
    }

    private fun parseResponse(response: NetworkResult<TrendingMovieResponse>) {
        when(response) {
            is NetworkResult.Error -> pushNewErrorState(resourceManager.getString(R.string.error_message))
            is NetworkResult.Exception -> pushNewErrorState(resourceManager.getString(R.string.exception_message))
            is NetworkResult.Success -> pushNewContentState(response.data.results)
        }
    }

    private fun pushNewErrorState(error: String) {
        state.tryEmit(state.value.copy(isLoading = false, error = error))
    }

    private fun pushNewContentState(movieList: List<TrendingMovieData>) {
        state.tryEmit(state.value.copy(isLoading = false, trendMovieList = movieList.map { it.toMovieData() }))
    }

    private fun handleMovieItemClick(movieData: MovieData) {
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
            ))
        }
    }
}