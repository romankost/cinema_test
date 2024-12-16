package com.romakost.trend_movie.movie_list.data

import com.romakost.core.toOneSymbolSting

fun TrendingMovieData.toMovieData() =
    MovieData(
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage.toOneSymbolSting(),
        releaseDate = releaseDate ?: ""
    )