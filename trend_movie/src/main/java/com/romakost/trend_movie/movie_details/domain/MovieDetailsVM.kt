package com.romakost.trend_movie.movie_details.domain

import androidx.lifecycle.ViewModel
import com.romakost.core.navigation.Navigator
import com.romakost.trend_movie.di.TrendMovieDetail
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_details.presentation.MovieDetailEvent
import com.romakost.trend_movie.movie_details.presentation.MovieDetailsState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Named

@HiltViewModel(assistedFactory = MovieDetailsVM.Factory::class)
class MovieDetailsVM
@AssistedInject  constructor(
//    private val resourceManager: ResourceManager,
   @Assisted private val rout: TrendMovieDetail,
   @Named("MovieListNavigator")
   private val navigation: Navigator
) : ViewModel() {
    private val state = MutableStateFlow(MovieDetailsState.initState)
    val movieDetailsScreenState = state.asStateFlow()

    private val effect = Channel<MovieDetailEvent>(Channel.BUFFERED)
    val movieDetailsEffect = effect.receiveAsFlow()

    init {
        pushNewState(rout.args)
    }

    fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.Back -> navigation.goBack()
        }
    }

    private fun pushNewState(args: MovieDetailsArgs) {
        val newState = state.value.copy(
            name = args.name,
            description = args.description,
            posterUrl = args.posterUrl,
            voteAverage = args.voteAverage,
            releaseDate = args.releaseDate
        )

        state.tryEmit(newState)
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: TrendMovieDetail): MovieDetailsVM
    }
}
