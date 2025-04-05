package com.romakost.favorites.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(user: Favorite)

    @Query("SELECT * FROM favorites")
    fun getAllMovie(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE favorites.movieName == :movieName")
    suspend fun getMovieByMovieName(movieName: String): Favorite?

    @Query("DELETE FROM favorites WHERE favorites.movieName = :movieName")
    suspend fun deleteMovie(movieName: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}