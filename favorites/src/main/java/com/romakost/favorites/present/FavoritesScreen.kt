package com.romakost.favorites.present

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.romakost.core.ui.MovieThumbNail
import com.romakost.favorites.data.db.Favorite

@InternalComposeApi
@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
) {
    if (state.favorites.isNotEmpty()) {
        Column {
            Button(onClick = { onEvent(FavoritesEvent.ClearAll) }) {
                Text("Clear All")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(count = state.favorites.size) {
                    FavoritesItem(state.favorites[it], onEvent)
                }
            }
        }
    }
}

@Composable
fun FavoritesItem(item: Favorite, onEvent: (FavoritesEvent) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .clickable { onEvent(FavoritesEvent.OnMovieItemClicked(item)) }
    ) {
        MovieThumbNail(
            url = item.posterPath,
            desc = item.movieName,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
        )
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = item.movieName,
        )
    }
}