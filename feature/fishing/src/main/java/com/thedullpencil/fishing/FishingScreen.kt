package com.thedullpencil.fishing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thedullpencil.core.ui.components.EmptyListMessage
import com.thedullpencil.core.ui.components.InfoCardListBlock
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.theme.Dimens
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.domain.model.Fish


@Composable
fun FishingScreen(
    viewModel: FishingViewModel,
    onFishClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val fishUiState by viewModel.uiState.collectAsState()
    FishingScreenContent(uiState = fishUiState, onFishClick = onFishClick, modifier = modifier)
}

@Composable
fun FishingScreenContent(
    uiState: FishingViewState,
    onFishClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is FishingViewState.FishingInfo -> FishList(uiState.fish, onFishClick, modifier)
        is FishingViewState.Loading -> CircularProgressIndicator()
        is FishingViewState.Empty -> EmptyListMessage(modifier)
    }
}

@Composable
fun FishList(fishList: List<Fish>, onFishClick: (String) -> Unit, modifier: Modifier = Modifier) = InfoCardListBlock(
    modifier.padding(Dimens.PaddingL.toDp()),
    items = fishList.toInfoItemList(onFishClick)
)

fun List<Fish>.toInfoItemList(onFishClick: (String) -> Unit): List<InfoItem> = this.map {
    InfoItem(name = it.name, modifier = Modifier.clickable { onFishClick(it.id) })
}

@Preview(showBackground = true)
@Composable
private fun FishingScreenEmptyPreview() {
    FishingScreenContent(
        uiState = FishingViewState.Empty,
        onFishClick = {},
    )
}
