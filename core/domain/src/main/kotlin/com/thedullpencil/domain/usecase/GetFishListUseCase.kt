package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.toSeason
import com.thedullpencil.data.model.FishData
import com.thedullpencil.data.repository.FishRepository
import com.thedullpencil.domain.model.Fish
import com.thedullpencil.domain.usecase.SortField.NAME
import com.thedullpencil.domain.usecase.SortField.NONE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFishListUseCase @Inject constructor(
    private val fishRepository: FishRepository
) {
    /**
     * Returns a list of fish
     * @param sortBy - the field used to sort the list items. Default NONE = no sorting.
     */
    operator fun invoke(sortBy: SortField = NONE): Flow<List<Fish>> = flow {
        val fish = fishRepository.getAllFish().map { fishData: FishData ->
            with(fishData) {
                Fish(name, description, price, location, size, season.toSeason(), weather, rarity, museum, diveable)
            }
        }

        emit(
            when (sortBy) {
                NAME -> fish.sortedBy { it.name }
                NONE -> fish
            }
        )
    }
}