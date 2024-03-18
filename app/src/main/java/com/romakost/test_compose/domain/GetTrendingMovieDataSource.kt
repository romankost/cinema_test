package com.romakost.test_compose.domain

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.romakost.test_compose.data.network.MovieService
import com.romakost.test_compose.data.network.TrendingMovieData
import java.io.IOException

class TrendMovieDataSource constructor(
    private val service: MovieService
) : PagingSource<Int, TrendingMovieData>()  {

    override fun getRefreshKey(state: PagingState<Int, TrendingMovieData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingMovieData> {
        return try {
            val currentPage = params.key ?: 1
            val moviesList = service.getTrendMovieList(currentPage)
            LoadResult.Page(
                data = moviesList?.results?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (moviesList?.results.isNullOrEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}