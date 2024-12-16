package com.romakost.trend_movie.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.romakost.network.R
import com.romakost.trend_movie.movie_list.present.ui.IMAGE_URL

@Composable
fun MovieThumbNail(url: String, desc: String, modifier: Modifier = Modifier) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_URL + url)
            .crossfade(true)
            .scale(Scale.FILL)
            .memoryCacheKey(url)
            .diskCacheKey(url)
            .placeholder(R.drawable.placeholder)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),

        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = desc
    )
}