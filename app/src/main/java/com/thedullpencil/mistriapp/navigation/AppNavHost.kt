package com.thedullpencil.mistriapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thedullpencil.mistriapp.HomeScreen
import com.thedullpencil.mistriapp.navigation.NavigationItem.Home
import com.thedullpencil.mistriapp.navigation.NavigationItem.Villagers
import com.thedullpencil.villagers.VillagerScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Home.route
) = NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
){
    homeNavGraph()
    villagerNavGraph()
}

fun NavGraphBuilder.homeNavGraph() = composable(route = Home.route){ HomeScreen()}
fun NavGraphBuilder.villagerNavGraph() = composable(Villagers.route) { VillagerScreen()}