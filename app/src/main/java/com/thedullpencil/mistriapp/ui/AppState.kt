package com.thedullpencil.mistriapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavDestination
import androidx.navigation.navOptions
import com.thedullpencil.home.navigation.HOME_ROUTE
import com.thedullpencil.mistriapp.navigation.TopLevelDestination
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Home
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Villagers
import com.thedullpencil.home.navigation.navigateToHome
import com.thedullpencil.villagers.navigation.VILLAGERS_ROUTE
import com.thedullpencil.villagers.navigation.navigateToVillagers

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
): AppState = remember(navController) { AppState(navController) }

@Stable
class AppState(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> Home
            VILLAGERS_ROUTE -> Villagers
            else -> null
        }

    /**
     * Map of top level destinations. Key is Route
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                Home -> navController.navigateToHome(topLevelNavOptions)
                Villagers -> navController.navigateToVillagers(null, topLevelNavOptions)
            }
        }
    }
}