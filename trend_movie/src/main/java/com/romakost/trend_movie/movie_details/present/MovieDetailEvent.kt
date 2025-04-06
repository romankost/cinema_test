package com.romakost.trend_movie.movie_details.present

sealed class MovieDetailEvent {
    data object Back : MovieDetailEvent()
}
