package com.thedullpencil.fishing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.thedullpencil.core.ui.components.InfoCardListBlock
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.theme.Dimens
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.domain.model.Fish


@Composable
fun FishingScreen(viewModel: FishingViewModel, modifier: Modifier = Modifier) {
    val fishUiState by viewModel.uiState.collectAsState()
    FishingScreenContent(uiState = fishUiState, modifier = modifier)
}

@Composable
fun FishingScreenContent(uiState: FishingViewState, modifier: Modifier = Modifier) {
    when (uiState) {
        is FishingViewState.FishingInfo -> FishList(uiState.fish, modifier)
        is FishingViewState.Loading -> CircularProgressIndicator()
        is FishingViewState.Empty -> FishList(emptyList(), modifier)
    }
}

@Composable
fun FishList(fishList: List<Fish>, modifier: Modifier = Modifier) = InfoCardListBlock(
    modifier.padding(Dimens.PaddingL.toDp()),
    items = fishList.toInfoItemList()
)

fun List<Fish>.toInfoItemList(): List<InfoItem> = this.map { InfoItem(name = it.name) }
