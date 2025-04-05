package com.romakost.trend_movie.movie_list.present

data class MovieItemViewState(
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String,
    val isLiked: Boolean
)