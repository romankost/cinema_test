package com.romakost.test_compose.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.romakost.test_compose.data.CinemaRepoImpl
import com.romakost.test_compose.data.network.TrendingMovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieListScreenState(
    val trendMovieList: Flow<PagingData<TrendingMovieData>>? = null,
    val movieText: String = "",
    val isLoading: Boolean = true
) {
    companion object {
        val initState = MovieListScreenState()
    }
}

@HiltViewModel
class MovieListScreenVM
@Inject constructor(
    private val repo: CinemaRepoImpl
) : ViewModel() {
    private val state = MutableStateFlow(MovieListScreenState.initState)
    val movieListScreenState = state.asStateFlow()

    init {
        fetchMovie()
    }

    private fun fetchMovie() {
        viewModelScope.launch {
            state.value = state.value.copy(trendMovieList = repo.fetchTrendingMovie().cachedIn(viewModelScope))

//            repo.fetchTrendingMovie().collect {
//                state.value = state.value.copy(trendMovieList = it, isLoading = false)
//            }
//            repo.fetchTrendingMovie().collectLatest {
//                state.value = state.value.copy(trendMovieList = it, isLoading = false)
//            }
        }
    }
}