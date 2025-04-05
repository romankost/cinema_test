package com.romakost.trend_movie.movie_list.data.network

import com.romakost.core.network.NetworkResult
import com.romakost.trend_movie.movie_list.data.TrendingMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface CinemaRepo {
    suspend fun fetchMovie(): Flow<NetworkResult<TrendingMovieResponse>>
}

class MovieRepoImpl @Inject constructor(
    private val service: MovieService
) : CinemaRepo {

    override suspend fun fetchMovie(): Flow<NetworkResult<TrendingMovieResponse>> {
        return flowOf(service.getTrendMovieList(1))
    }
}