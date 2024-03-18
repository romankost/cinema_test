package com.romakost.test_compose.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.romakost.test_compose.data.network.MovieService
import com.romakost.test_compose.data.network.TrendingMovieData
import com.romakost.test_compose.domain.TrendMovieDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CinemaRepo {
    suspend fun fetchTrendingMovie(): Flow<PagingData<TrendingMovieData>>
}

class CinemaRepoImpl @Inject constructor(
    private val service: MovieService
) : CinemaRepo {

    override suspend fun fetchTrendingMovie() =
        Pager(
            config = PagingConfig(1000),
            pagingSourceFactory = { TrendMovieDataSource(service) }
        ).flow

}