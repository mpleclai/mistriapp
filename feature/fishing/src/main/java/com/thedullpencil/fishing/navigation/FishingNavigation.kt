package com.thedullpencil.fishing.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.FishingDetail
import com.thedullpencil.core.navigation.TopLevelRoute.Fishing
import com.thedullpencil.fishing.FishingScreen
import com.thedullpencil.fishing.detail.FishingDetailScreen

fun NavController.navigateToFishing(navOptions: NavOptions? = null) = navigate(Fishing, navOptions)

fun NavController.navigateToFishingDetail(fishName: String, navOptions: NavOptions? = null) =
    navigate(FishingDetail(fishName), navOptions)

fun NavGraphBuilder.fishingScreen(onFishClick: (String) -> Unit) = composable<Fishing> {
    FishingRoute(onFishClick = onFishClick)
}

fun NavGraphBuilder.fishingDetailScreen(onBackClick: () -> Unit) =
    composable<FishingDetail> { FishingDetailRoute(onBackClick = onBackClick) }

@Composable
fun FishingRoute(onFishClick: (String) -> Unit) = FishingScreen(hiltViewModel(), onFishClick)

@Composable
fun FishingDetailRoute(onBackClick: () -> Unit) = FishingDetailScreen(hiltViewModel(), onBackClick)
