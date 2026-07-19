package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.toDay
import com.thedullpencil.core.util.Day
import com.thedullpencil.core.util.Season
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.repository.VillagerRepository
import com.thedullpencil.domain.model.Villager
import javax.inject.Inject

class GetVillagerDetailUseCase @Inject constructor(
    private val villagerRepository: VillagerRepository,
) {
    suspend operator fun invoke(name: String): Villager? {
        val data = villagerRepository.getVillager(name) ?: return null
        return Villager(
            name = data.name,
            birthday = data.toBirthday(),
            job = data.job,
            dateable = data.dateable,
            lovedGifts = data.lovedGifts,
            likedGifts = data.likedGifts,
            hatedGift = data.hatedGift,
            dislikedGiftTags = data.dislikedGiftTags,
        )
    }
}

fun VillagerData.toBirthday(): Day =
    toDay(birthdaySeason, birthdayDay) ?: Day(Season.Spring, 1)
