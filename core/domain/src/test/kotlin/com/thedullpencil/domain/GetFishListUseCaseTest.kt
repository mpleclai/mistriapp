package com.thedullpencil.domain

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season
import com.thedullpencil.data.model.FishData
import com.thedullpencil.data.repository.FishRepository
import com.thedullpencil.domain.model.Fish
import com.thedullpencil.domain.usecase.GetFishListUseCase
import com.thedullpencil.domain.usecase.SortField
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

private const val NAME_A = "Anchovy"
private const val NAME_B = "Bass"

class GetFishListUseCaseTest {
    private val fishOne = FishData(
        name = NAME_B,
        description = "desc b",
        price = "100",
        location = "river",
        size = "small",
        season = "Spring \\u00a0Fall",
        weather = "sunny",
        rarity = "common",
        museum = "yes",
        diveable = "no"
    )
    private val fishTwo = FishData(
        name = NAME_A,
        description = "desc a",
        price = "200",
        location = "lake",
        size = "medium",
        season = "All",
        weather = "rain",
        rarity = "rare",
        museum = "yes",
        diveable = "yes"
    )

    private val fishRepository = mockk<FishRepository> {
        coEvery { this@mockk.getAllFish() } returns listOf(fishOne, fishTwo)
    }

    private val getFishListUseCase by lazy { GetFishListUseCase(fishRepository) }

    @Test
    fun `fish maps correctly and keeps unsorted default order`() = runTest {
        val fish = getFishListUseCase().first()

        assertEquals(
            Fish(
                name = NAME_B,
                description = "desc b",
                price = "100",
                location = "river",
                size = "small",
                season = listOf(Spring, Fall),
                weather = "sunny",
                rarity = "common",
                museum = "yes",
                diveable = "no"
            ),
            fish.first()
        )
        assertEquals(listOf(NAME_B, NAME_A), fish.map { it.name })
    }

    @Test
    fun `fish can be sorted by name`() = runTest {
        val fish = getFishListUseCase(SortField.NAME).first()

        assertEquals(listOf(NAME_A, NAME_B), fish.map { it.name })
        assertEquals(Season.entries, fish.first().season)
        assertEquals(listOf(Spring, Fall), fish.last().season)
    }
}
