package com.thedullpencil.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.domain.model.Season
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerListUseCase
import com.thedullpencil.domain.usecase.SortField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        flowOf(HomeUiState.HomeInfo(date = Pair(Season.Spring, 1))).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading,
        )

}

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class HomeInfo(
        val date: Pair<Season, Int> = Pair(Season.Spring, 1)
    ) : HomeUiState

    data object Empty : HomeUiState
}
