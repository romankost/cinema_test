package com.romakost.trend_movie.movie_list.present

import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs

sealed class MovieListEffect {
    data class NavigateToShowDetail(val args: MovieDetailsArgs) : MovieListEffect()
}
