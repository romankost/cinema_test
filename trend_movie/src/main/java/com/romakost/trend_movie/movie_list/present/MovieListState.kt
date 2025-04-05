package com.romakost.trend_movie.movie_list.present

import com.romakost.trend_movie.movie_list.data.MovieData

data class MovieListState (
    val trendMovieList: List<MovieItemViewState> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = ""
) {
    val isError = error.isNotEmpty()

    companion object {
        val initState = MovieListState()
    }
}