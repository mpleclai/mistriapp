package com.thedullpencil.villagers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.villagers.VillagerViewState.Empty
import com.thedullpencil.villagers.VillagerViewState.Loading
import com.thedullpencil.villagers.VillagerViewState.VillagersInfo

@Composable
fun VillagerScreen(
    onVillagerClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VillagerViewModel = hiltViewModel(),
) {
    val villagersUiState by viewModel.uiState.collectAsState()

    when (villagersUiState) {
        is VillagersInfo ->
            VillagerList((villagersUiState as VillagersInfo).villagers, onVillagerClick, modifier)

        is Loading -> CircularProgressIndicator()
        is Empty -> VillagerList(emptyList(), onVillagerClick)
    }
}

@Composable
fun VillagerList(
    villagerList: List<Villager>,
    onVillagerClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) = InfoBlock(
    modifier.padding(PaddingL.toDp()),
    header = "Villagers",
    items = villagerList.toInfoItemList(onVillagerClick)
)

fun List<Villager>.toInfoItemList(onVillagerClick: (String) -> Unit): List<InfoItem> = this.map {
    InfoItem(name = it.name, modifier = Modifier.clickable { onVillagerClick(it.name) })
}
