package com.thedullpencil.villagers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.TopLevelRoute.Villagers
import com.thedullpencil.core.navigation.VillagerDetail
import com.thedullpencil.villagers.VillagerScreen
import com.thedullpencil.villagers.VillagerViewModel
import com.thedullpencil.villagers.detail.VillagerDetailScreen

fun NavController.navigateToVillagers(navOptions: NavOptions? = null) =
    navigate(Villagers, navOptions)

fun NavController.navigateToVillagerDetail(villagerName: String, navOptions: NavOptions? = null) =
    navigate(VillagerDetail(villagerName), navOptions)

fun NavGraphBuilder.villagersScreen(onVillagerClick: (String) -> Unit) = composable<Villagers> {
    VillagersRoute(onVillagerClick = onVillagerClick)
}

fun NavGraphBuilder.villagerDetailScreen(onBackClick: () -> Unit) =
    composable<VillagerDetail> { VillagerDetailRoute(onBackClick = onBackClick) }

@Composable
fun VillagersRoute(onVillagerClick: (String) -> Unit, modifier: Modifier = Modifier) =
    VillagerScreen(hiltViewModel<VillagerViewModel>(), onVillagerClick, modifier)

@Composable
fun VillagerDetailRoute(onBackClick: () -> Unit, modifier: Modifier = Modifier) =
    VillagerDetailScreen(hiltViewModel(), onBackClick, modifier)
