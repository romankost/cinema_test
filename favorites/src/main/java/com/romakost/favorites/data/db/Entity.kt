package com.romakost.favorites.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val movieName: String,
    val posterPath: String
)