package com.thedullpencil.villagers.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.core.navigation.VillagerDetail
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface VillagerDetailViewState {
    data object Loading : VillagerDetailViewState
    data class Detail(val villager: Villager) : VillagerDetailViewState
    data object NotFound : VillagerDetailViewState
}

@HiltViewModel
class VillagerDetailViewModel @Inject constructor(
    private val getVillagerDetailUseCase: GetVillagerDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val villagerName: String = checkNotNull(
        savedStateHandle.get<String>(VillagerDetail.NAME_ARG)
    )

    private val _uiState =
        MutableStateFlow<VillagerDetailViewState>(VillagerDetailViewState.Loading)

    val uiState: StateFlow<VillagerDetailViewState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val villager = getVillagerDetailUseCase(villagerName)
            _uiState.value = if (villager != null) {
                VillagerDetailViewState.Detail(villager)
            } else {
                VillagerDetailViewState.NotFound
            }
        }
    }
}
