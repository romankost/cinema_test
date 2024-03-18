package com.romakost.test_compose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.romakost.test_compose.data.network.TrendingMovieData

@Composable
fun MovieItem(data: TrendingMovieData, navController: NavController) {
    Row(
        Modifier
            .clickable { navController.navigate(com.romakost.common_ui.NavigationItem.Detail.route) }
    ) {
        Box(
            Modifier
                .height(60.dp)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(data.title)
        }
    }
}