package com.thedullpencil.domain

import com.thedullpencil.core.util.Day
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.repository.VillagerRepository
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerListUseCase
import com.thedullpencil.domain.usecase.SortField
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

private const val ZOE = "Zoe"
private const val ALEX = "Alex"
private const val BIRTHDAY_1 = 11
private const val BIRTHDAY_2 = 14

class GetVillagerListUseCaseTest {
    private val villager1 = VillagerData(
        name = ZOE,
        birthdaySeason = Summer.name,
        birthdayDay = BIRTHDAY_1,
        job = "Baker",
        dateable = true,
        lovedGifts = listOf("cake"),
        likedGifts = listOf("bread"),
        hatedGift = "fish",
        dislikedGiftTags = listOf("junk"),
    )
    private val villager2 = VillagerData(
        name = ALEX,
        birthdaySeason = Spring.name,
        birthdayDay = BIRTHDAY_2,
        job = "Farmer",
    )

    private val villagerListData = listOf(villager1, villager2)

    private val expectedVillager1 = Villager(
        name = ZOE,
        birthday = Day(Summer, BIRTHDAY_1),
        job = "Baker",
        dateable = true,
        lovedGifts = listOf("cake"),
        likedGifts = listOf("bread"),
        hatedGift = "fish",
        dislikedGiftTags = listOf("junk"),
    )
    private val expectedVillager2 = Villager(
        name = ALEX,
        birthday = Day(Spring, BIRTHDAY_2),
        job = "Farmer",
    )

    private val emptyList = emptyList<VillagerData>()

    private val villagerRepository = mockk<VillagerRepository> {
        coEvery { this@mockk.getVillagers() } returns villagerListData
    }

    private val emptyVillagerRepository = mockk<VillagerRepository> {
        coEvery { this@mockk.getVillagers() } returns emptyList
    }

    private val getVillagerListUseCase by lazy { GetVillagerListUseCase(villagerRepository) }

    private val getVillagerListUseCaseEmpty by lazy { GetVillagerListUseCase(emptyVillagerRepository) }

    @Test
    fun `data maps correctly for list and keeps unsorted default order`() = runTest {
        val villagers = getVillagerListUseCase.invoke()
        assertEquals(expectedVillager1, villagers.first().first())
        assertEquals(expectedVillager2, villagers.first()[1])
        assertEquals(listOf(ZOE, ALEX), villagers.first().map { it.name })
    }

    @Test
    fun `villagers can be sorted by name`() = runTest {
        val villagers = getVillagerListUseCase(SortField.NAME).first()

        assertEquals(listOf(ALEX, ZOE), villagers.map { it.name })
    }

    @Test
    fun verifyReturnedDeals() = runTest {
        val villagers = getVillagerListUseCaseEmpty.invoke()
        assertEquals(villagers.first(), emptyList)
    }
}
