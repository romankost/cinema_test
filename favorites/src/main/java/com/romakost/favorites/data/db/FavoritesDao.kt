package com.romakost.favorites.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(user: FavoritesEntity)

    @Query("SELECT * FROM favorites")
    fun getAllMovie(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites WHERE favorites.movieName == :movieName")
    suspend fun getMovieByMovieName(movieName: String): FavoritesEntity?

    @Query("DELETE FROM favorites WHERE favorites.movieName = :movieName")
    suspend fun deleteMovie(movieName: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}
