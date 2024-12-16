package com.romakost.trend_movie.movie_list.present

import com.romakost.trend_movie.movie_list.data.MovieData

sealed class MovieListEvent {
    data class OnMovieItemClicked(val movieData: MovieData): MovieListEvent()
}