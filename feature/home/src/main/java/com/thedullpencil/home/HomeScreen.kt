package com.thedullpencil.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.components.ToInfoCard
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.Dimens.PaddingS
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.feature.home.R.string.feature_home_select_profile
import com.thedullpencil.home.HomeUiState.Empty
import com.thedullpencil.home.HomeUiState.HomeInfo

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val homeUiState by viewModel.uiState.collectAsState()
    HomeScreenContent(uiState = homeUiState)
}

@Composable
fun HomeScreenContent(uiState: HomeUiState) {
    Column(Modifier.padding(PaddingL.toDp())) {
        when (uiState) {
            is Empty -> EmptyHomeScreen()
            is HomeInfo -> HomeInfoContent(uiState)
            else -> CircularProgressIndicator()
        }
    }
}

@Composable
private fun HomeInfoContent(uiState: HomeInfo) {
    DateWidget(uiState.selectedProfile.currentDate, uiState.selectedProfile.currentYear)
    Spacer(Modifier.padding(PaddingS.toDp()))
    ProfileCard(uiState.selectedProfile.name)
    Spacer(Modifier.padding(PaddingL.toDp()))
    RemindersBlock()
}

@Composable
private fun EmptyHomeScreen() {
    val selectProfile = stringResource(feature_home_select_profile)
    Card(Modifier.fillMaxWidth()) {
        InfoItem(name = selectProfile, icon = Filled.Add).ToInfoCard(false)
    }
}
