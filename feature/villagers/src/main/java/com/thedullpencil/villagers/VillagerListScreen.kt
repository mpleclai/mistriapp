package com.thedullpencil.villagers

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.ui.theme.Dimens
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.data.model.Villager

@Composable
fun VillagerListScreen(
    villagerListViewModel: VillagerListViewModel = viewModel()
) {
    val villagerListUiState by villagerListViewModel.uiState.collectAsState()
    val sortedVillagerList = villagerListUiState.villagerList.sortedBy { it.name }
    InfoBlock(Modifier.padding(Dimens.PaddingL.toDp()), header = "Villagers", items = sortedVillagerList.toInfoItemList())
}

fun List<Villager>.toInfoItemList(): List<InfoItem> = this.map { InfoItem(name = it.name) }

@Preview
@Composable
fun VillagerListPreview() {
    val villagerList =
        listOf(Villager("March"), Villager("Adeline"), Villager("Ryis")).sortedBy { it.name }
    InfoBlock(header = "Villagers", items = villagerList.toInfoItemList())
}