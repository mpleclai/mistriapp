package com.thedullpencil.mistriapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.thedullpencil.fishing.navigation.fishingScreen
import com.thedullpencil.home.navigation.HOME_ROUTE
import com.thedullpencil.home.navigation.homeScreen
import com.thedullpencil.mistriapp.ui.AppState
import com.thedullpencil.museum.navigation.museumScreen
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
        museumScreen()
        fishingScreen()
    }
}
