package com.thedullpencil.villagers

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thedullpencil.data.model.Villager
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VillagerListScreenTest {
    private val testList = listOf(
        Villager("March"), Villager("Adeline"), Villager("Ryis")
    ).sortedBy { it.name }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `list items display`() {
        composeTestRule.setContent {
            VillagerListScreen(/*fake ui state*/)
        }
    }
}