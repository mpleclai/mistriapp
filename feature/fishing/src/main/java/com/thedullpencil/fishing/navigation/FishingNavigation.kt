package com.thedullpencil.fishing.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.TopLevelRoute.Fishing
import com.thedullpencil.fishing.FishingScreen

fun NavController.navigateToFishing(navOptions: NavOptions? = null) = navigate(Fishing, navOptions)

fun NavGraphBuilder.fishingScreen() = composable<Fishing> { FishingRoute() }

@Composable
fun FishingRoute() = FishingScreen(hiltViewModel())
