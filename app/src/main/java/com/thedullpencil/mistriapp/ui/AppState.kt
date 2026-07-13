package com.thedullpencil.mistriapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.thedullpencil.core.navigation.TopLevelRoute.Fishing
import com.thedullpencil.core.navigation.TopLevelRoute.Home
import com.thedullpencil.core.navigation.TopLevelRoute.Museum
import com.thedullpencil.core.navigation.TopLevelRoute.Villagers
import com.thedullpencil.mistriapp.navigation.TopLevelDestination
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Fishing
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Home
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Museum
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Villagers

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): AppState = remember(navController) { AppState(navController) }

@Stable
class AppState(val navController: NavHostController) {
    val currentDestination: androidx.navigation.NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = currentDestination?.let { dest ->
            when {
                dest.hasRoute<Home>() -> Home
                dest.hasRoute<Villagers>() -> Villagers
                dest.hasRoute<Museum>() -> Museum
                dest.hasRoute<Fishing>() -> Fishing
                else -> null
            }
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }

            navController.navigate(topLevelDestination.route, topLevelNavOptions)
        }
    }
}
