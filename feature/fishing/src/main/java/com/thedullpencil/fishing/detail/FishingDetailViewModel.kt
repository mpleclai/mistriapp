package com.thedullpencil.fishing.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.core.navigation.FishingDetail
import com.thedullpencil.core.navigation.FishingDetail.Companion.NAME_ARG
import com.thedullpencil.domain.model.Fish
import com.thedullpencil.domain.usecase.GetFishDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface FishingDetailViewState {
    data object Loading : FishingDetailViewState
    data class Detail(val fish: Fish) : FishingDetailViewState
    data object NotFound : FishingDetailViewState
}

@HiltViewModel
class FishingDetailViewModel @Inject constructor(
    private val getFishDetailUseCase: GetFishDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val fishName: String = checkNotNull(savedStateHandle.get<String>(NAME_ARG))

    private val _uiState =
        MutableStateFlow<FishingDetailViewState>(FishingDetailViewState.Loading)
    val uiState: StateFlow<FishingDetailViewState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val fish = getFishDetailUseCase(fishName)
            _uiState.value = if (fish != null) {
                FishingDetailViewState.Detail(fish)
            } else {
                FishingDetailViewState.NotFound
            }
        }
    }
}
