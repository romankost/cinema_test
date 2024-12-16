package com.romakost.trend_movie.movie_details.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsArgs(
    val name: String,
    val description: String,
    val posterUrl: String,
    val voteAverage: String,
    val releaseDate: String
)