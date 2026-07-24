package com.thedullpencil.fishing.detail

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_bait_only
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_legendary
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_locations
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_not_found
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_perk_artifact
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_rarity
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_retrieval
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_seasons
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_size
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_water_type
import com.thedullpencil.core.ui.R.string.core_ui_fish_detail_weather
import com.thedullpencil.core.ui.components.DetailInfoCard
import com.thedullpencil.core.ui.components.DetailTopAppBar
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.components.NotFoundHandler
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.Dimens.PaddingM
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.core.util.toDisplayName
import com.thedullpencil.domain.model.Fish
import com.thedullpencil.fishing.detail.FishingDetailViewState.Detail
import com.thedullpencil.fishing.detail.FishingDetailViewState.Loading
import com.thedullpencil.fishing.detail.FishingDetailViewState.NotFound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishingDetailScreen(
    viewModel: FishingDetailViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        is Loading -> CircularProgressIndicator()
        is NotFound -> NotFoundHandler(stringResource(core_ui_fish_detail_not_found), onBackClick)
        is Detail -> FishingDetailContent(state.fish, onBackClick, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FishingDetailContent(
    fish: Fish,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { DetailTopAppBar(fish.name, onBackClick) },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = PaddingL.toDp(), vertical = PaddingM.toDp()),
            verticalArrangement = spacedBy(PaddingM.toDp()),
        ) {
            DetailInfoCard(stringResource(core_ui_fish_detail_seasons)) {
                Text(fish.seasons.joinToString(", "))
            }
            DetailInfoCard(stringResource(core_ui_fish_detail_water_type)) {
                Text(fish.waterType.joinToString(", ") { it.toDisplayName() })
            }
            DetailInfoCard(stringResource(core_ui_fish_detail_weather)) {
                val display = fish.weather.ifEmpty { listOf("Any") }
                Text(display.joinToString(", ") { it.toDisplayName() })
            }
            if (fish.locations.isNotEmpty()) {
                DetailInfoCard(stringResource(core_ui_fish_detail_locations)) {
                    Text(fish.locations.joinToString(", ") { it.toDisplayName() })
                }
            }
            DetailInfoCard(stringResource(core_ui_fish_detail_size)) {
                Text(fish.size.toDisplayName())
            }
            DetailInfoCard(stringResource(core_ui_fish_detail_rarity)) {
                Text(fish.rarity.toDisplayName())
            }
            DetailInfoCard(stringResource(core_ui_fish_detail_retrieval)) {
                Text(fish.retrieval.joinToString(", ") { it.toDisplayName() })
            }
            if (fish.legendary) {
                LegendaryCard(stringResource(core_ui_fish_detail_legendary), true)
            }
            fish.perkArtifact?.let {
                DetailInfoCard(stringResource(core_ui_fish_detail_perk_artifact)) {
                    Text(it.toDisplayName())
                }
            }
            if (fish.baitOnly) {
                LegendaryCard(stringResource(core_ui_fish_detail_bait_only), true)
            }
        }
    }
}

@Composable
private fun LegendaryCard(title: String, active: Boolean) {
    val icon = if (active) Icons.Filled.Check else Icons.Filled.Close
    val tint = if (active) colorScheme.primary else colorScheme.error
    DetailInfoCard(title) {
        Icon(icon, contentDescription = title, tint = tint)
    }
}
