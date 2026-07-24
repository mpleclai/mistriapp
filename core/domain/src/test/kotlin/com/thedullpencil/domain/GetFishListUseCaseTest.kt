package com.thedullpencil.domain

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.core.util.Season.Winter
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

private const val NAME_A = "anchovy"
private const val NAME_B = "bass"

class GetFishListUseCaseTest {
    private val fishOne = FishData(
        name = NAME_B,
        item = NAME_B,
        seasons = listOf("spring", "fall"),
        waterType = listOf("river"),
        weather = null,
        locations = null,
        size = "small",
        legendary = false,
        rarity = "common",
        retrieval = listOf("fishing"),
        isChest = false,
        recipe = false,
        perkArtifact = null,
        hasPerk = null,
        baitOnly = false,
    )
    private val fishTwo = FishData(
        name = NAME_A,
        item = NAME_A,
        seasons = null,
        waterType = listOf("ocean"),
        weather = listOf("inclement"),
        locations = listOf("beach"),
        size = "medium",
        legendary = true,
        rarity = "rare",
        retrieval = listOf("fishing", "divespot"),
        isChest = false,
        recipe = false,
        perkArtifact = null,
        hasPerk = "legendary",
        baitOnly = false,
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
                id = NAME_B,
                name = "Bass",
                item = NAME_B,
                seasons = listOf(Spring, Fall),
                waterType = listOf("river"),
                weather = emptyList(),
                locations = emptyList(),
                size = "small",
                legendary = false,
                rarity = "common",
                retrieval = listOf("fishing"),
                isChest = false,
                perkArtifact = null,
                hasPerk = null,
                baitOnly = false,
            ),
            fish.first()
        )
        assertEquals(listOf("Bass", "Anchovy"), fish.map { it.name })
    }

    @Test
    fun `fish can be sorted by name`() = runTest {
        val fish = getFishListUseCase(SortField.NAME).first()

        assertEquals(listOf("Anchovy", "Bass"), fish.map { it.name })
        assertEquals(Season.entries, fish.first().seasons)
        assertEquals(listOf(Spring, Fall), fish.last().seasons)
    }
}
