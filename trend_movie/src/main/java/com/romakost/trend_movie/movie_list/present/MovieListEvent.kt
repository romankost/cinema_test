package com.romakost.trend_movie.movie_list.present

interface MovieListEvent {
    data class OnMovieItemClicked(val movieData: MovieItemViewState) : MovieListEvent
    data class AddOnFavorites(val movieData: MovieItemViewState) : MovieListEvent
}
