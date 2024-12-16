package com.romakost.trend_movie.movie_list.di

import com.romakost.trend_movie.movie_list.data.network.TMDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class TrendMovieNetwork {

    @Provides
    fun provideTMDB(retrofit: Retrofit): TMDBApi {
        return retrofit.create(TMDBApi::class.java)
    }
}
