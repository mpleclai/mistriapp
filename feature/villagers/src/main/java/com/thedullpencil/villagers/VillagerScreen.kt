package com.thedullpencil.villagers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thedullpencil.common.components.InfoBlock
import com.thedullpencil.common.components.InfoItem
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.data.model.Villager

@Composable
fun VillagerScreen(
    uiState: VillagersUiState,
    onVillagerClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) = when (uiState) {
    is VillagersUiState.Loading -> CircularProgressIndicator()
    is VillagersUiState.VillagersInfo -> VillagerList(uiState.villagers, onVillagerClick, modifier)
    is VillagersUiState.Empty -> VillagerList(emptyList(), onVillagerClick)
}

@Preview
@Composable
fun VillagerListPreview() {
    val villagerList =
        listOf(Villager("March"), Villager("Adeline"), Villager("Ryis")).sortedBy { it.name }
    InfoBlock(header = "Villagers", items = villagerList.toInfoItemList({}))
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
