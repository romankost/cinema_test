package com.romakost.test_compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.romakost.core.BottomNavigationItem
import com.romakost.profile.navigateToProfile
import com.romakost.trend_movie.navigateToMovieDetail
import com.romakost.trend_movie.navigateToTrendMovie
import com.romakost.favorites.navigateToFavorites

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavigationItem.Movie,
        BottomNavigationItem.Favorites,
        BottomNavigationItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .heightIn(30.dp)
    ) {
        bottomNavigationItems.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(it.icon),
                        contentDescription = it.route,
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                    )
                },
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = currentRoute == it.route,
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.White,
                    indicatorColor = Color.White
                ),
            )
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = BottomNavigationItem.Movie.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigateToTrendMovie(navController)
        navigateToFavorites()
        navigateToProfile()
        navigateToMovieDetail(navController)
    }
}