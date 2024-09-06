package com.thedullpencil.villagers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thedullpencil.villagers.VillagerScreen

@Composable
fun VillagersRoute(onVillagerClick: (String) -> Unit, modifier: Modifier = Modifier) {
    VillagerScreen(onVillagerClick, modifier)
}