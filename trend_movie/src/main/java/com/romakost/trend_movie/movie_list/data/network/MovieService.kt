package com.romakost.trend_movie.movie_list.data.network

import com.romakost.core.network.NetworkResult
import com.romakost.trend_movie.movie_list.data.TrendingMovieResponse
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: TMDBApi,
) {

    suspend fun getTrendMovieList(pageNumber: Int): NetworkResult<TrendingMovieResponse> {
        return api.getTrendingMovie(pageNumber)
    }
}
