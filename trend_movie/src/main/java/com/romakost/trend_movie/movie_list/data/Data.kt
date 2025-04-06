package com.romakost.trend_movie.movie_list.data

import com.google.gson.annotations.SerializedName

data class TrendingMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TrendingMovieData>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class TrendingMovieData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

// TODO: Need to remove
data class MovieData(
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String
)
