package com.example.responsi.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{mealId}") {
        fun createRoute(mealId: String) = "detail/$mealId"
    }
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
}
