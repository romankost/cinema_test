package com.romakost.test_compose.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBApi {

    @GET("trending/movie/day?language=en-US")
    suspend fun getTrendingMovie(
       @Query("page") page: Int = 1
    ): Response<TrendingMovieResponse>
}