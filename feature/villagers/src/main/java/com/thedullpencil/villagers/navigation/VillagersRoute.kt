package com.thedullpencil.villagers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thedullpencil.villagers.VillagerScreen
import com.thedullpencil.villagers.VillagersViewModel

@Composable
fun VillagersRoute(
    onVillagerClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VillagersViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VillagerScreen(uiState, onVillagerClick, modifier)
}