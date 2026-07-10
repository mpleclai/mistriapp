package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.Season
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.toSeason
import com.thedullpencil.data.model.FishData
import com.thedullpencil.data.repository.FishRepository
import com.thedullpencil.domain.model.Fish
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
    operator fun invoke(sortBy: SortField = SortField.NAME): Flow<List<Fish>> = flow {
        emit(
            fishRepository.getAllFish().map { fishData: FishData ->
                with(fishData) {
                    Fish(name, description, price, location, size, season.toSeason(), weather, rarity, museum, diveable)
                }
            }.sortedBy {
                when (sortBy) {
                    SortField.NAME -> it.name
                    SortField.NONE -> null
                }
            }
        )
    }
}