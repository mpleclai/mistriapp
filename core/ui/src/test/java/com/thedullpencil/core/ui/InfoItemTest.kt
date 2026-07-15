package com.thedullpencil.core.ui

import android.content.Context
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.components.ToListItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = getInstrumentation().targetContext

    /* Test Strings */
    private val name = context.getString(R.string.core_ui_name_tag)
    private val value = context.getString(R.string.core_ui_value_tag)
    private val divider = context.getString(R.string.core_ui_divider_tag)
    private val icon = context.getString(R.string.core_ui_icon_tag)

    /* Test Items */
    private val emptyItem = InfoItem()
    private val fullItem = InfoItem(Modifier, name, value, Filled.AccountCircle)

    /* Test Nodes */
    private val nameNode = composeTestRule.onNodeWithText(name)
    private val valueNode = composeTestRule.onNodeWithText(value)
    private val dividerNode = composeTestRule.onNodeWithTag(divider)
    private val iconNode = composeTestRule.onNodeWithTag(icon, useUnmergedTree = true)

    @Test
    fun `empty InfoItem - nothing displays`() {
        composeTestRule.setContent {
            emptyItem.ToListItem(showDivider = false)
        }
        nameNode.assertDoesNotExist()
        iconNode.assertDoesNotExist()
        dividerNode.assertDoesNotExist()
        valueNode.assertDoesNotExist()
    }

    @Test
    fun `full InfoItem - all elements display`() {
        composeTestRule.setContent {
            fullItem.ToListItem(showDivider = true)
        }
        nameNode.assertIsDisplayed()
        dividerNode.assertIsDisplayed()
        valueNode.assertIsDisplayed()
        iconNode.assertIsDisplayed()
    }
}