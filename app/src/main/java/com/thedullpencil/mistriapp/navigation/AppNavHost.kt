package com.thedullpencil.mistriapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.thedullpencil.core.navigation.TopLevelRoute.Home
import com.thedullpencil.fishing.navigation.fishingScreen
import com.thedullpencil.fishing.navigation.fishingDetailScreen
import com.thedullpencil.fishing.navigation.navigateToFishingDetail
import com.thedullpencil.home.navigation.homeScreen
import com.thedullpencil.mistriapp.ui.AppState
import com.thedullpencil.museum.navigation.museumScreen
import com.thedullpencil.villagers.navigation.navigateToVillagerDetail
import com.thedullpencil.villagers.navigation.villagerDetailScreen
import com.thedullpencil.villagers.navigation.villagersScreen

@Composable
fun AppNavHost(appState: AppState, modifier: Modifier = Modifier) {
    val navController = appState.navController
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        homeScreen()
        villagersScreen(onVillagerClick = { navController.navigateToVillagerDetail(it) })
        villagerDetailScreen(onBackClick = { navController.popBackStack() })
        museumScreen()
        fishingScreen(onFishClick = { navController.navigateToFishingDetail(it) })
        fishingDetailScreen(onBackClick = { navController.popBackStack() })
    }
}
