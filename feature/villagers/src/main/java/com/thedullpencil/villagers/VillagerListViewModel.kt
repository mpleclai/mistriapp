package com.thedullpencil.villagers

import androidx.lifecycle.ViewModel
import com.thedullpencil.data.model.Villager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//@HiltViewModel
class VillagerListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(VillagerListUiState())
    val uiState: StateFlow<VillagerListUiState> = _uiState.asStateFlow()
}
data class VillagerListUiState(
    val villagerList: List<Villager> = emptyList()
)