package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.Day
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.getMistriappDate
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.repository.VillagerRepository
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.SortField.NONE
import com.thedullpencil.domain.usecase.SortField.NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVillagerListUseCase @Inject constructor(
    private val villagersRepository: VillagerRepository,
) {
    /**
     * Returns a list of villagers
     * @param sortBy - the field used to sort the list items. Default NONE = no sorting.
     */
    operator fun invoke(sortBy: SortField = NONE): Flow<List<Villager>> = flow {
        val villagers = villagersRepository.getVillagers().map { villagerData: VillagerData ->
            with(villagerData) {
                Villager(
                    name = name,
                    birthday = getMistriappDate(birthdaySeason, birthdayDay)
                        ?: Day(Spring, 1).also {
                            // TODO maybe wire up some actual logging here
                            System.err.println(
                                "GetVillagerListUseCase: unrecognized season " +
                                    "'$birthdaySeason' for '$name', fell back to Spring 1"
                            )
                        },
                    job = job,
                    dateable = dateable,
                    lovedGifts = lovedGifts,
                    likedGifts = likedGifts,
                    hatedGift = hatedGift,
                    dislikedGiftTags = dislikedGiftTags,
                )
            }
        }

        emit(
            when (sortBy) {
                NAME -> villagers.sortedBy { it.name }
                NONE -> villagers
            }
        )
    }
}
