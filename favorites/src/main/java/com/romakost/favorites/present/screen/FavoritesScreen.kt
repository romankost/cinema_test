package com.romakost.favorites.present.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romakost.core.ui.MovieThumbNail
import com.romakost.core.ui.theme.CinemaAppTheme
import com.romakost.favorites.data.db.FavoritesEntity
import com.romakost.favorites.present.FavoritesEvent
import com.romakost.favorites.present.FavoritesState
import com.romakost.tv_show.R

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
) {
    when {
        state.isNoFavorite -> NoFavoritesItemState()
        else -> FavoritesItemList(
            state = state,
            onEvent = onEvent
        )
    }
}

@Composable
private fun FavoritesItemList(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ClearAll(onEvent = onEvent)

        LazyColumn {
            items(count = state.favorites.size) {
                FavoritesItem(state.favorites[it], onEvent)
            }
        }
    }
}

@Composable
private fun ClearAll(onEvent: (FavoritesEvent) -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 26.dp),
        onClick = { onEvent(FavoritesEvent.ClearAll) }
    ) {
        Text(stringResource(R.string.clear_all))
    }
}

@Composable
private fun FavoritesItem(item: FavoritesEntity, onEvent: (FavoritesEvent) -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(200.dp)
            .clickable { onEvent(FavoritesEvent.OnMovieItemClicked(item)) }
    ) {
        MovieThumbNail(
            url = item.posterPath,
            desc = item.movieName,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            text = item.movieName
        )
    }
}

@Composable
private fun NoFavoritesItemState() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.no_favorites))
    }
}

@Preview(name = "Light Mode")
@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    CinemaAppTheme {
        val mockFavorites = listOf(
            FavoritesEntity(
                id = 1,
                movieName = "Inception",
                posterPath = "/5HJqjCTcaE1TFwnNh3Dn21be2es.jpg"
            ),
            FavoritesEntity(
                id = 2,
                movieName = "The Matrix",
                posterPath = "/5HJqjCTcaE1TFwnNh3Dn21be2es.jpg"
            )
        )
        val state = FavoritesState(favorites = mockFavorites)

        FavoritesScreen(state = state) { }
    }
}
