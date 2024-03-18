package com.romakost.test_compose.data.network

import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: TMDBApi
)  {

    suspend fun getTrendMovieList(pageNumber: Int) =
         api.getTrendingMovie().body()
}