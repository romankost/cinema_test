package com.romakost.trend_movie.movie_list.present.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romakost.core.ui.MovieThumbNail
import com.romakost.core.ui.theme.CinemaAppTheme
import com.romakost.trend_movie.R
import com.romakost.trend_movie.movie_list.present.MovieItemViewState
import com.romakost.trend_movie.movie_list.present.MovieListEvent
import com.romakost.trend_movie.shared.ui.RatingView

@Composable
fun MovieItemView(data: MovieItemViewState, onEvent: (MovieListEvent) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .clickable { onEvent(MovieListEvent.OnMovieItemClicked(data)) },

    ) {
        MovieThumbNail(
            url = data.posterPath,
            desc = data.title,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),

        ) {
            IconButton(onClick = { onEvent(MovieListEvent.AddOnFavorites(data)) }) {
                val likeColor = if (data.isLiked) Color.Red else Color.Black
                Icon(
                    painter = painterResource(R.drawable.like),
                    contentDescription = "like",
                    tint = likeColor,
                )
            }
            RatingView(data.voteAverage)
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = data.title,
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(showBackground = true)
@Composable
fun MovieItemViewPreview() {
    CinemaAppTheme {
        val movieStab = MovieItemViewState(
            title = "Example Movie",
            overview = "This is a brief description of the movie, giving an idea of the plot and context.",
            posterPath = "/5HJqjCTcaE1TFwnNh3Dn21be2es.jpg",
            releaseDate = "2024-12-25",
            voteAverage = "7.8",
            isLiked = true,
        )

        MovieItemView(data = movieStab) { }
    }
}
