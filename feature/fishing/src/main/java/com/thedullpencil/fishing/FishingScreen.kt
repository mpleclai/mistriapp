package com.thedullpencil.fishing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thedullpencil.core.ui.components.InfoBlock
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.theme.Dimens
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.domain.model.Fish

@Composable
fun FishingScreen(
    modifier: Modifier = Modifier,
    viewModel: FishingViewModel = hiltViewModel(),
) {
    val fishUiState by viewModel.uiState.collectAsState()

    when (val state = fishUiState) {
        is FishingViewState.FishingInfo -> FishList(state.fish, modifier)
        is FishingViewState.Loading -> CircularProgressIndicator()
        is FishingViewState.Empty -> FishList(emptyList(), modifier)
    }
}

@Composable
fun FishList(
    fishList: List<Fish>,
    modifier: Modifier = Modifier,
) = InfoBlock(
    modifier.padding(Dimens.PaddingL.toDp()),
    header = "Fish",
    items = fishList.toInfoItemList()
)

fun List<Fish>.toInfoItemList(): List<InfoItem> = this.map {
    InfoItem(name = it.name)
}