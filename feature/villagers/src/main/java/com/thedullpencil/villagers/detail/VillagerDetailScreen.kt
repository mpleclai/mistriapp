package com.thedullpencil.villagers.detail

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
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_birthday
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_dateable
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_dateable_no
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_dateable_yes
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_disliked_tags
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_hated_gift
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_liked_gifts
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_loved_gifts
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_none
import com.thedullpencil.core.ui.R.string.core_ui_villager_detail_not_found
import com.thedullpencil.core.ui.components.DetailInfoCard
import com.thedullpencil.core.ui.components.DetailListCard
import com.thedullpencil.core.ui.components.DetailTopAppBar
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.components.NotFoundHandler
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.Dimens.PaddingM
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.core.util.toDateString
import com.thedullpencil.core.util.toDisplayName
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.villagers.detail.VillagerDetailViewState.Detail
import com.thedullpencil.villagers.detail.VillagerDetailViewState.Loading
import com.thedullpencil.villagers.detail.VillagerDetailViewState.NotFound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillagerDetailScreen(
    viewModel: VillagerDetailViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        is Loading -> CircularProgressIndicator()
        is NotFound -> NotFoundHandler(
            stringResource(core_ui_villager_detail_not_found),
            onBackClick
        )

        is Detail -> VillagerDetailContent(state.villager, onBackClick, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VillagerDetailContent(
    villager: Villager,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(
    topBar = { DetailTopAppBar(villager.name, onBackClick) },
    modifier = modifier,
) { innerPadding ->
    val noneText = stringResource(core_ui_villager_detail_none)
    Column(
        Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = PaddingL.toDp(), vertical = PaddingM.toDp()),
        verticalArrangement = spacedBy(PaddingM.toDp()),
    ) {
        DetailInfoCard(stringResource(core_ui_villager_detail_birthday)) {
            Text(villager.birthday.toDateString())
        }
        DetailInfoCard(stringResource(core_ui_villager_detail_dateable)) {
            DateableIcon(villager.dateable)
        }
        for (section in villager.giftSections()) {
            DetailListCard(
                stringResource(section.headerRes),
                section.gifts.toGiftItems(noneText)
            )
        }
        DetailInfoCard(stringResource(core_ui_villager_detail_hated_gift)) {
            Text(villager.hatedGift?.toDisplayName() ?: noneText)
        }
    }
}


private data class GiftSection(val headerRes: Int, val gifts: List<String>)

private fun Villager.giftSections(): List<GiftSection> = listOf(
    GiftSection(core_ui_villager_detail_loved_gifts, lovedGifts),
    GiftSection(core_ui_villager_detail_liked_gifts, likedGifts),
    GiftSection(core_ui_villager_detail_disliked_tags, dislikedGiftTags),
)

@Composable
private fun DateableIcon(dateable: Boolean) {
    val (icon, description, tint) = if (dateable) {
        Triple(
            Icons.Filled.Check,
            stringResource(core_ui_villager_detail_dateable_yes),
            colorScheme.primary,
        )
    } else {
        Triple(
            Icons.Filled.Close,
            stringResource(core_ui_villager_detail_dateable_no),
            colorScheme.error,
        )
    }
    Icon(icon, description, tint = tint)
}

private fun List<String>.toGiftItems(noneText: String): List<InfoItem> =
    if (isEmpty()) listOf(InfoItem(name = noneText))
    else map { InfoItem(name = it.toDisplayName()) }
