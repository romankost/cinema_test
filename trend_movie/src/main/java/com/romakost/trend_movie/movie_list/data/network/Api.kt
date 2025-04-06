package com.romakost.trend_movie.movie_list.data.network

import com.romakost.core.network.NetworkResult
import com.romakost.trend_movie.movie_list.data.TrendingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("trending/movie/day?language=en-US")
    suspend fun getTrendingMovie(
        @Query("page") page: Int = 1
    ): NetworkResult<TrendingMovieResponse>
}
