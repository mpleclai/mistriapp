package com.thedullpencil.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thedullpencil.domain.model.Profile
import com.thedullpencil.domain.usecase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
//    var selectedDate: MistriappDate by mutableStateOf(MistriappDate(Season.Spring, 1))
//        private set
//
//    var selectedProfile: Profile? by mutableStateOf(null)
//        private set
//
//    internal fun setDate(date: MistriappDate) {
//        selectedDate = date
//    }
//
//    internal fun setProfile(profile: Profile) {
//        selectedProfile = profile
//    }

    val profileId: String? = "1"

    val uiState: StateFlow<HomeUiState> = getProfileUseCase(id = profileId)
        .map(HomeUiState::HomeInfo)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Loading,
        )

//    init {
//        profileId?.let {
//            fetchProfile(it)
//        }
//    }
//
//    private fun fetchProfile(id: String) = viewModelScope.launch {
//        getProfileUseCase(id).collect {
//            setDate(it.currentDate)
//            setProfile(it)
//        }
//    }
}

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class HomeInfo(
        val selectedProfile: Profile,
//        val homeViewState: HomeViewState,
//        val events: List<Event>
//        val reminders: List<Reminder>
    ) : HomeUiState

    data object Empty : HomeUiState
}

