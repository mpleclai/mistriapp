package com.thedullpencil.villagers

import androidx.lifecycle.ViewModel
import com.thedullpencil.data.model.Villager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//@HiltViewModel
class VillagersViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(VillagersUiState())
    val uiState: StateFlow<VillagersUiState> = _uiState.asStateFlow()
}
data class VillagersUiState(
    val villagerList: List<Villager> = emptyList()
)