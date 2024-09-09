package com.thedullpencil.villagers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerListUseCase
import com.thedullpencil.domain.usecase.SortField.NONE
import com.thedullpencil.villagers.VillagerViewState.Loading
import com.thedullpencil.villagers.navigation.VILLAGER_NAME_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VillagerViewModel @Inject constructor(
    getVillagerListUseCase: GetVillagerListUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val selectedVillagerName: StateFlow<String?> =
        savedStateHandle.getStateFlow(VILLAGER_NAME_ARG, null)

    val uiState: StateFlow<VillagerViewState> = getVillagerListUseCase(sortBy = NONE)
        .map(VillagerViewState::VillagersInfo)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading,
        )

    fun onVillagerClick(villagerName: String) {
        savedStateHandle[VILLAGER_NAME_ARG] = villagerName
    }
}

sealed interface VillagerViewState {
    data object Loading : VillagerViewState

    data class VillagersInfo(val villagers: List<Villager> = emptyList()) : VillagerViewState

    data object Empty : VillagerViewState
}