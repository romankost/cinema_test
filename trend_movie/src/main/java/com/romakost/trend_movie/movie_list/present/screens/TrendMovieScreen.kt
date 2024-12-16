package com.romakost.trend_movie.movie_list.present.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.romakost.trend_movie.movie_list.data.MovieData
import com.romakost.trend_movie.movie_list.present.MovieListEffect
import com.romakost.trend_movie.movie_list.present.MovieListEvent
import com.romakost.trend_movie.movie_list.present.MovieListState
import com.romakost.trend_movie.movie_list.present.ui.MovieItemView
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieListScreen(
    navController: NavController,
    state: MovieListState,
    effect: Flow<MovieListEffect>,
    onEvent: (MovieListEvent) -> Unit
) {
    LaunchedEffect(effect) {
        effect.collect { effect ->
            when (effect) {
                is MovieListEffect.NavigateToShowDetail -> navController.navigate(effect.args)
            }
        }
    }

    when {
        state.isError -> ErrorState(state.error)
        state.isLoading -> LoadingState()
        else -> ContentState(state.trendMovieList, onEvent)
    }
}

@Composable
private fun ContentState(
    items: List<MovieData>,
    onEvent: (MovieListEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(count = items.size) {
            MovieItemView(
                items[it],
                onEvent
            )
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Loading...")
        }
    }
}

@Composable
fun ErrorState(message: String) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}