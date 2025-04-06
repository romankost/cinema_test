package com.romakost.trend_movie.movie_details.di

import com.romakost.trend_movie.movie_details.domain.MovieDetailsVM
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ViewModelFactoryProvider {

    fun movieDetailsVMFactory(): MovieDetailsVM.Factory
}
