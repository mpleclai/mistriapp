package com.thedullpencil.domain

import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.repository.VillagerRepository
import com.thedullpencil.domain.model.Villager
import com.thedullpencil.domain.usecase.GetVillagerListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

private const val NAME = "Name"
private const val NAME_2 = "Name2"
private const val BIRTHDAY_1 = 11
private const val BIRTHDAY_2 = 14

class GetVillagerListUseCaseTest {
    private val villager1 = VillagerData(NAME, Summer.name, BIRTHDAY_1)
    private val villager2 = VillagerData(NAME_2, Spring.name, BIRTHDAY_2)

    private val villagerListData = listOf(villager1, villager2)

    private val expectedVillager1 = Villager(NAME, MistriappDate(Summer, BIRTHDAY_1))
    private val expectedVillager2 = Villager(NAME_2, MistriappDate(Spring, BIRTHDAY_2))

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
    fun `data maps correctly for list`() = runTest {
        val villagers = getVillagerListUseCase.invoke()
        with(villagers.first().first()) {
            assertEquals(expectedVillager1.name, name)
            assertEquals(expectedVillager1.birthday, birthday)
        }
        with(villagers.first()[1]) {
            assertEquals(expectedVillager2.name, name)
            assertEquals(expectedVillager2.birthday, birthday)
        }
    }

    @Test
    fun verifyReturnedDeals() = runTest {
        val villagers = getVillagerListUseCaseEmpty.invoke()
        assertEquals(villagers.first(), emptyList)
    }
}