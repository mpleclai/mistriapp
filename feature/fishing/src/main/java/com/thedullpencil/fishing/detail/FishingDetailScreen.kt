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
import com.thedullpencil.core.ui.components.NotFoundHandler
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.Dimens.PaddingM
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.core.util.toDisplayName
import com.thedullpencil.core.util.toStringList
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
    modifier: Modifier = Modifier
) = Scaffold(
    topBar = { DetailTopAppBar(fish.name, onBackClick) },
    modifier = modifier,
) { innerPadding ->
    val seasonsLabel = stringResource(core_ui_fish_detail_seasons)
    val waterTypeLabel = stringResource(core_ui_fish_detail_water_type)
    val weatherLabel = stringResource(core_ui_fish_detail_weather)
    val locationsLabel = stringResource(core_ui_fish_detail_locations)
    val sizeLabel = stringResource(core_ui_fish_detail_size)
    val rarityLabel = stringResource(core_ui_fish_detail_rarity)
    val retrievalLabel = stringResource(core_ui_fish_detail_retrieval)
    val legendaryLabel = stringResource(core_ui_fish_detail_legendary)
    val perkArtifactLabel = stringResource(core_ui_fish_detail_perk_artifact)
    val baitOnlyLabel = stringResource(core_ui_fish_detail_bait_only)

    Column(
        Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = PaddingL.toDp(), vertical = PaddingM.toDp()),
        verticalArrangement = spacedBy(PaddingM.toDp()),
    ) {
        with(fish) {
            val weather = weather.ifEmpty { listOf("Any") }

            if (locations.isNotEmpty()) {
                DetailInfoCard(locationsLabel) { Text(locations.toStringList()) }
            }
            DetailInfoCard(seasonsLabel) { Text(seasons.map { it.toString() }.toStringList()) }
            DetailInfoCard(waterTypeLabel) { Text(waterType.toStringList()) }
            DetailInfoCard(weatherLabel) { Text(weather.toStringList()) }
            DetailInfoCard(sizeLabel) { Text(size.toDisplayName()) }
            DetailInfoCard(rarityLabel) { Text(rarity.toDisplayName()) }
            DetailInfoCard(retrievalLabel) { Text(retrieval.toStringList()) }
            perkArtifact?.let { DetailInfoCard(perkArtifactLabel) { Text(it.toDisplayName()) } }
            if (legendary) BooleanCard(legendaryLabel, true)
            if (baitOnly) BooleanCard(baitOnlyLabel, true)
        }
    }
}


@Composable
private fun BooleanCard(title: String, active: Boolean) {
    val icon = if (active) Icons.Filled.Check else Icons.Filled.Close
    val tint = if (active) colorScheme.primary else colorScheme.error
    DetailInfoCard(title) {
        Icon(icon, contentDescription = title, tint = tint)
    }
}
