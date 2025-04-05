package com.romakost.trend_movie.movie_list.data

import com.romakost.core.toOneSymbolSting
import com.romakost.trend_movie.movie_list.present.MovieItemViewState

fun TrendingMovieData.toMovieItemViewState(isLiked: Boolean = false) =
    MovieItemViewState(
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage.toOneSymbolSting(),
        releaseDate = releaseDate ?: "",
        isLiked =  isLiked
    )