package com.romakost.trend_movie

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.romakost.core.BottomNavigationItem
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_details.present.MovieDetail
import com.romakost.trend_movie.movie_list.domain.MovieListVM
import com.romakost.trend_movie.movie_list.present.screens.MovieListScreen

fun NavGraphBuilder.navigateToTrendMovie(navController: NavController) {
    composable(route = BottomNavigationItem.Movie.route) {
        val vm = hiltViewModel<MovieListVM>()
        val state by vm.movieListScreenState.collectAsStateWithLifecycle()
        MovieListScreen(
            navController = navController,
            state = state,
            effect = vm.screenEffect,
            onEvent = vm::event
        )
    }
}

fun NavGraphBuilder.navigateToMovieDetail(navController: NavController) {
    composable<MovieDetailsArgs> { backStackEntry ->
        val args: MovieDetailsArgs = backStackEntry.toRoute()
        MovieDetail(navController ,args)
    }
}