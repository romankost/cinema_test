package com.romakost.trend_movie.movie_details.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.romakost.trend_movie.movie_details.data.MovieDetailsArgs
import com.romakost.trend_movie.movie_details.present.MovieDetailEvent
import com.romakost.trend_movie.movie_details.present.MovieDetailsState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MovieDetailsVM
@AssistedInject constructor(
    @Assisted private val args: MovieDetailsArgs
) : ViewModel() {
    private val state = MutableStateFlow(MovieDetailsState.initState)
    val movieDetailsScreenState = state.asStateFlow()

    private val effect = Channel<MovieDetailEvent>(Channel.BUFFERED)
    val movieDetailsEffect = effect.receiveAsFlow()

    init {
        pushNewState(args)
    }

    fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.Back -> viewModelScope.launch { effect.send(MovieDetailEvent.Back) }
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

/**
     * A helper setup for creating a `MovieDetailsVM` ViewModel instance using Dagger's `@AssistedInject`.
     *
     * This code facilitates assisted injection for ViewModel creation, allowing dependencies and runtime
     * arguments (e.g., `MovieDetailsArgs`) to be provided during ViewModel initialization.
     *
     * Usage Example:
     * ```
     * class MovieDetailsFragment : Fragment() {
     *     private val viewModel: MovieDetailsVM by viewModels {
     *         MovieDetailsVM.provideFactory(assistedFactory, args)
     *     }
     * }
     * ```
     */

    @AssistedFactory
    interface Factory {
        fun create(args: MovieDetailsArgs): MovieDetailsVM
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            args: MovieDetailsArgs
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(args) as T
            }
        }
    }
}
