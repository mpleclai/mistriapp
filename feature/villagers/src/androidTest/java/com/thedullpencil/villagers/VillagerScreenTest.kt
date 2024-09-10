package com.thedullpencil.villagers

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
@HiltAndroidTest
class VillagerScreenTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }
//    private val testList = listOf(
//        Villager("March", MistriappDate()), Villager("Adeline"), Villager("Ryis")
//    ).sortedBy { it.name }
}