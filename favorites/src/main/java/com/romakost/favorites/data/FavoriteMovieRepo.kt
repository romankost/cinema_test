package com.romakost.favorites.data

import com.romakost.favorites.data.db.FavoritesDao
import com.romakost.favorites.data.db.FavoritesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface FavoriteMovieRepo {
    suspend fun getAllFavorites(): Flow<List<FavoritesEntity>>

    suspend fun getFavoritesByMovieName(movieName: String): Flow<FavoritesEntity?>

    suspend fun isFavoritesExist(movieName: String): Flow<Boolean>

    suspend fun insertFavorite(favorite: FavoritesEntity): Flow<Unit>

    suspend fun deleteFavoritesByMovieName(movieName: String): Flow<Unit>

    suspend fun deleteAllFavorites(): Flow<Unit>
}

class FavoriteMovieRepoImp @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoriteMovieRepo {

    override suspend fun getAllFavorites() = favoritesDao.getAllMovie()

    override suspend fun getFavoritesByMovieName(movieName: String) = flowOf(
        favoritesDao.getMovieByMovieName(movieName)
    )

    override suspend fun isFavoritesExist(movieName: String) = flowOf(
        favoritesDao.getMovieByMovieName(movieName) != null
    )

    override suspend fun insertFavorite(favorite: FavoritesEntity) = flowOf(favoritesDao.insertMovie(favorite))

    override suspend fun deleteFavoritesByMovieName(movieName: String) = flowOf(favoritesDao.deleteMovie(movieName))

    override suspend fun deleteAllFavorites() = flowOf(favoritesDao.deleteAllFavorites())
}
