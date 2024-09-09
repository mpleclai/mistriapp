package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.repository.VillagerRepository
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.SortField.NAME
import com.thedullpencil.domain.usecase.SortField.NONE
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
    operator fun invoke(sortBy: SortField = NAME): Flow<List<Villager>> = flow {
        emit(
            villagersRepository.getVillagers().map { villagerData: VillagerData ->
                with(villagerData) { Villager(name = name, birthday = MistriappDate(Spring, 1)) }
            }.sortedBy {
                when (sortBy) {
                    NAME -> it.name
                    NONE -> null
                }
            }
        )
    }
}

enum class SortField { NONE, NAME }
