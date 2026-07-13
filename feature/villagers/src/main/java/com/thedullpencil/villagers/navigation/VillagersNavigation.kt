package com.thedullpencil.villagers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.TopLevelRoute.Villagers
import com.thedullpencil.villagers.VillagerScreen

const val VILLAGER_NAME_ARG = "villagerName"

fun NavController.navigateToVillagers(villagerName: String? = null, navOptions: NavOptions? = null)
    = navigate(Villagers(villagerName), navOptions)


fun NavGraphBuilder.villagersScreen(onVillagerClick: (String) -> Unit) = composable<Villagers> {
    VillagersRoute(onVillagerClick = onVillagerClick)
}

@Composable
fun VillagersRoute(onVillagerClick: (String) -> Unit, modifier: Modifier = Modifier)
    = VillagerScreen(hiltViewModel(), onVillagerClick, modifier)
