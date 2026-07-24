package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.toDisplayName
import com.thedullpencil.core.util.toSeasonList
import com.thedullpencil.data.repository.FishRepository
import com.thedullpencil.domain.model.Fish
import javax.inject.Inject

class GetFishDetailUseCase @Inject constructor(
    private val fishRepository: FishRepository,
) {
    suspend operator fun invoke(rawName: String): Fish? {
        val data = fishRepository.getFishData(rawName) ?: return null
        return Fish(
            id = data.name,
            name = data.name.toDisplayName(),
            item = data.item,
            seasons = data.seasons.toSeasonList(),
            waterType = data.waterType,
            weather = data.weather.orEmpty(),
            locations = data.locations.orEmpty(),
            size = data.size,
            legendary = data.legendary,
            rarity = data.rarity,
            retrieval = data.retrieval,
            isChest = data.isChest,
            perkArtifact = data.perkArtifact,
            hasPerk = data.hasPerk,
            baitOnly = data.baitOnly,
        )
    }
}
