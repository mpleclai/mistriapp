package com.thedullpencil.fishing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.domain.model.Fish
import com.thedullpencil.domain.usecase.GetFishListUseCase
import com.thedullpencil.domain.usecase.SortField.NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val TIMEOUT = 5_000L

@HiltViewModel
class FishingViewModel @Inject constructor(
    getFishListUseCase: GetFishListUseCase,
) : ViewModel() {
    val uiState: StateFlow<FishingViewState> = getFishListUseCase(sortBy = NAME)
        .map { fish ->
            if (fish.isEmpty()) {
                FishingViewState.Empty
            } else {
                FishingViewState.FishingInfo(fish)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT),
            initialValue = FishingViewState.Loading
        )
}

sealed interface FishingViewState {
    data object Loading : FishingViewState

    data class FishingInfo(val fish: List<Fish> = emptyList()) : FishingViewState

    data object Empty : FishingViewState
}