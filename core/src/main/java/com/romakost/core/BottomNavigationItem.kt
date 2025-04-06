package com.romakost.core

import com.romakost.network.R

sealed class BottomNavigationItem(val route: String, val icon: Int) {
    data object Movie : BottomNavigationItem("movie", R.drawable.icon_movie)
    data object Favorites : BottomNavigationItem("favorites", R.drawable.icon_list_heart)
    data object Profile : BottomNavigationItem("profile", R.drawable.icon_prof)
}
