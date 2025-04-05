package com.romakost.favorites.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}