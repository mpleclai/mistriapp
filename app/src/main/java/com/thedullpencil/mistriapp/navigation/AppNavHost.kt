package com.thedullpencil.mistriapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.thedullpencil.mistriapp.ui.AppState
import com.thedullpencil.villagers.navigation.villagersScreen

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        homeScreen()
        villagersScreen{}
    }
}
