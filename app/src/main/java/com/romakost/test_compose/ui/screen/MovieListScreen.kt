package com.romakost.test_compose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.romakost.test_compose.data.network.TrendingMovieData
import com.romakost.test_compose.domain.MovieListScreenState
import com.romakost.test_compose.domain.MovieListScreenVM
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieListScreen(
    navController: NavController,
    state: StateFlow<MovieListScreenState>,
    vm: MovieListScreenVM
) {
    with(state.collectAsState().value) {
        val lazyItems = trendMovieList?.collectAsLazyPagingItems()
        lazyItems?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> LoadingState()
                is LoadState.Error -> ErrorState()
                else -> ResultState(lazyItems, navController)
            }
        }
    }
}

@Composable
private fun ResultState(
    lazyItems: LazyPagingItems<TrendingMovieData>?,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(count = lazyItems?.itemCount ?: 0) {
            MovieItem(lazyItems!![it]!!, navController)
            Divider()
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
fun ErrorState() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("NetworkError")
    }
}