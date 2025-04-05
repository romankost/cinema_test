package com.romakost.trend_movie.movie_details.present

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.romakost.core.ui.theme.CinemaAppTheme
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_details.di.ViewModelFactoryProvider
import com.romakost.trend_movie.movie_details.domain.MovieDetailsVM
import com.romakost.core.ui.MovieThumbNail
import com.romakost.trend_movie.shared.ui.RatingView
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieDetail(
    navigation: NavController,
    args: MovieDetailsArgs
) {
    val vm = MovieDetailsVM(args)
    val state by vm.movieDetailsScreenState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        navController = navigation,
        state = state,
        effect = vm.movieDetailsEffect,
        onEvent = vm::event
    )
}

@Composable
fun MovieDetailScreen(
    navController: NavController,
    state: MovieDetailsState,
    effect: Flow<MovieDetailEvent>,
    onEvent: (MovieDetailEvent) -> Unit
) {
    LaunchedEffect(effect) {
        effect.collect { effect ->
            when (effect) {
                is MovieDetailEvent.Back -> navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.height(300.dp)) {
            MovieThumbNail(
                url = state.posterUrl,
                desc = state.name,
                modifier = Modifier
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { onEvent(MovieDetailEvent.Back) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }

                Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    RatingView(state.voteAverage)
                    Text(
                        state.name,
                        Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(state.releaseDate)
                }
            }
        }

        Text(state.description, Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
    }
}

@Composable
fun movieDetailsVM(args: MovieDetailsArgs): MovieDetailsVM {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).movieDetailsVMFactory()

    return viewModel(factory = MovieDetailsVM.provideFactory(factory, args))
}

@Preview(name = "Light Mode")
@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
    val mockArgs = MovieDetailsArgs(
        name = "Dirty Angels",
        description = "During the United States' 2021 withdrawal from Afghanistan, " +
                "a group of female soldiers posing as medical relief are sent back in to" +
                " rescue a group of kidnapped teenagers caught between ISIS and the Taliban.",
        posterUrl = "/5HJqjCTcaE1TFwnNh3Dn21be2es.jpg",
        releaseDate = "2024-12-11",
        voteAverage = "7.0",
    )

    val mockNavController = NavController(LocalContext.current)

    CinemaAppTheme {
        MovieDetail(mockNavController, mockArgs)
    }
}


