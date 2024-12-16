package com.romakost.trend_movie.shared.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romakost.core.ui.theme.CinemaAppTheme

@Composable
fun RatingView(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        Image(
            imageVector = Icons.Default.Star,
            contentDescription = rating,
            colorFilter = ColorFilter.tint(Color.Yellow),
            modifier = Modifier
                .height(12.dp)
                .width(12.dp)
        )
        Text(
            text = rating,
            modifier = Modifier.padding(horizontal = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
    }
}

@Preview(name = "Light Mode")
@Preview(showBackground = true)
@Composable
fun RatingPreview() {
    CinemaAppTheme {
        RatingView(rating = "4.5")
    }
}
