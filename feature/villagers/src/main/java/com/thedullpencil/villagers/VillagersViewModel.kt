package com.thedullpencil.villagers

import com.thedullpencil.domain.SortField
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.data.model.Villager
import com.thedullpencil.domain.GetVillagerListUseCase
import com.thedullpencil.villagers.navigation.VILLAGER_NAME_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VillagersViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    getVillagerList: GetVillagerListUseCase
) : ViewModel() {

    val selectedVillagerName: StateFlow<String?> =
        savedStateHandle.getStateFlow(VILLAGER_NAME_ARG, null)

    val uiState: StateFlow<VillagersUiState> = getVillagerList(sortBy = SortField.NAME)
        .map(VillagersUiState::VillagersInfo)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = VillagersUiState.Loading,
        )


    fun onVillagerClick(villagerName: String) {
        savedStateHandle[VILLAGER_NAME_ARG] = villagerName
    }
}

sealed interface VillagersUiState {
    data object Loading : VillagersUiState

    data class VillagersInfo(
        val villagers: List<Villager> = emptyList()
    ) : VillagersUiState

    data object Empty : VillagersUiState
}