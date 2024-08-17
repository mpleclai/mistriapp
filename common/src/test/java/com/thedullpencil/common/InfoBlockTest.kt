package com.thedullpencil.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoBlockTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `empty list InfoBlock - nothing displays`() {
    }

    @Test
    fun `large list InfoBlock - all elements display`() {
    }
}