package com.romakost.test_compose

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.romakost.common_ui.BottomNavigationItem
import com.romakost.common_ui.NavigationItem
import com.romakost.common_ui.Screen
import com.romakost.profile.navigateToProfile
import com.romakost.test_compose.domain.MovieListScreenVM
import com.romakost.test_compose.ui.screen.DetailScreen
import com.romakost.test_compose.ui.screen.MovieListScreen
import com.romakost.trend_movie.navigateToTrendMovie

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavigationItem.Movie,
        BottomNavigationItem.TvShow,
        BottomNavigationItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar() {
        bottomNavigationItems.forEach {
            NavigationBarItem(
                selected = true,
                label = { Text(text = it.label) },
                icon =  { Icon(imageVector = it.icon, contentDescription = it.route)},
                onClick = { navController.navigate(it.route) },
                alwaysShowLabel = currentRoute == it.route
            )
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,

    ) {
    NavHost(navController = navController, startDestination = startDestination ) {
        composable(BottomNavigationItem.Movie.route) {
            val vm = hiltViewModel<MovieListScreenVM>()
            MovieListScreen(
                navController = navController,
                state = vm.movieListScreenState,
                vm = vm)
        }

        navigateToTrendMovie()
        navigateToProfile()

        composable(Screen.DETAIL.name) {
            DetailScreen(navController = navController)
        }
    }
}