package com.romakost.trend_movie.movie_details.present

data class MovieDetailsState(
    val name: String = "",
    val description: String = "",
    val posterUrl: String = "",
    val voteAverage: String = "",
    val releaseDate: String = ""
) {
    companion object {
        val initState = MovieDetailsState()
    }
}
