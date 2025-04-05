package com.romakost.favorites.di

import android.content.Context
import androidx.room.Room
import com.romakost.favorites.data.FavoriteMovieRepo
import com.romakost.favorites.data.FavoriteMovieRepoImp
import com.romakost.favorites.data.db.FavoritesDao
import com.romakost.favorites.data.db.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoritesDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoritesDatabase::class.java,
            "room_database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: FavoritesDatabase) : FavoritesDao {
        return appDatabase.favoritesDao()
    }
    @Provides
    @Singleton
    fun provideTaskRepository(favoritesDao: FavoritesDao): FavoriteMovieRepo {
        return FavoriteMovieRepoImp(favoritesDao)
    }
}