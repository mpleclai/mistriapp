package com.thedullpencil.villagers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val VILLAGER_NAME_ARG = "villagerName"
const val VILLAGERS_ROUTE_BASE = "villagers_route"
const val VILLAGERS_ROUTE = "$VILLAGERS_ROUTE_BASE?$VILLAGER_NAME_ARG={$VILLAGER_NAME_ARG}"

fun NavController.navigateToVillagers(
    villagerName: String? = null,
    navOptions: NavOptions? = null
) {
    val route = if (villagerName != null) {
        "${VILLAGERS_ROUTE_BASE}?${VILLAGER_NAME_ARG}=$villagerName"
    } else {
        VILLAGERS_ROUTE_BASE
    }
    navigate(route, navOptions)
}

fun NavGraphBuilder.villagersScreen(onVillagerClick: (String) -> Unit) = composable(
    route = VILLAGERS_ROUTE,
    arguments = listOf(
        navArgument(VILLAGER_NAME_ARG) {
            defaultValue = null
            nullable = true
            type = NavType.StringType
        },
    ),
) { VillagersRoute(onVillagerClick = onVillagerClick) }
