package com.example.responsi.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.responsi.ui.components.BottomNavBar
import com.example.responsi.ui.detail.DetailScreen
import com.example.responsi.ui.favorite.FavoriteScreen
import com.example.responsi.ui.home.HomeScreen
import com.example.responsi.ui.home.HomeUiState
import com.example.responsi.ui.home.HomeViewModel
import com.example.responsi.ui.profile.ProfileScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Check if current route should show bottom navigation bar
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Favorites.route,
        Screen.Profile.route
    )

    val favoriteMealIds by homeViewModel.favoriteMealIds.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()

    val favoriteMeals = if (uiState is HomeUiState.Success) {
        (uiState as HomeUiState.Success).meals.filter {
            favoriteMealIds.contains(it.id)
        }
    } else {
        emptyList()
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onMealClick = { mealId ->
                        navController.navigate(Screen.Detail.createRoute(mealId))
                    },
                    viewModel = homeViewModel
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("mealId") { type = NavType.StringType })
            ) { backStackEntry ->
                val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                DetailScreen(
                    mealId = mealId,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoriteScreen(
                    favoriteMeals = favoriteMeals,
                    onMealClick = { mealId ->
                        navController.navigate(Screen.Detail.createRoute(mealId))
                    },
                    onFavoriteToggle = { meal ->
                        homeViewModel.toggleFavorite(meal)
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
