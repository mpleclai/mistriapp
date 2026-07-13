package com.thedullpencil.villagers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thedullpencil.villagers.VillagerScreen

@Composable
fun VillagersRoute(onVillagerClick: (String) -> Unit, modifier: Modifier = Modifier) {
    VillagerScreen(
        viewModel = hiltViewModel(),
        onVillagerClick = onVillagerClick,
        modifier = modifier,
    )
}
