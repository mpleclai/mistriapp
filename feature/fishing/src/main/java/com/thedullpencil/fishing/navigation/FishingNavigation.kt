package com.thedullpencil.fishing.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.fishing.FishingScreen

const val FISHING_ROUTE = "fishing_route"

fun NavController.navigateToFishing(navOptions: NavOptions? = null) { navigate(FISHING_ROUTE, navOptions) }

fun NavGraphBuilder.fishingScreen() = composable(route = FISHING_ROUTE) { FishingRoute() }

@Composable
fun FishingRoute() { FishingScreen() }