package com.thedullpencil.villagers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerListUseCase
import com.thedullpencil.domain.usecase.SortField.NONE
import com.thedullpencil.villagers.VillagerViewState.Empty
import com.thedullpencil.villagers.VillagerViewState.VillagersInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VillagerViewModel @Inject constructor(
    getVillagerListUseCase: GetVillagerListUseCase,
) : ViewModel() {
    val uiState: StateFlow<VillagerViewState> = getVillagerListUseCase(sortBy = NONE)
        .map { list ->
            if (list.isEmpty()) Empty else VillagersInfo(list)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = VillagerViewState.Loading,
        )
}

sealed interface VillagerViewState {
    data object Loading : VillagerViewState

    data class VillagersInfo(val villagers: List<Villager> = emptyList()) : VillagerViewState

    data object Empty : VillagerViewState
}
