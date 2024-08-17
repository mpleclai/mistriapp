package com.thedullpencil.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.thedullpencil.common.R
import com.thedullpencil.common.R.string.divider_tag
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp

@Immutable
data class InfoItem(
    val modifier: Modifier = Modifier,
    val name: String? = "",
    val value: String? = "",
    val icon: ImageVector? = null
)

@Composable
fun InfoItem.ToInfoCard(showDivider: Boolean = true) {
    ListItem(
        headlineContent = { name?.let { Text(it) } },
        modifier = modifier,
        leadingContent = { icon?.let { Icon(it, null, Modifier.iconTag()) } },
        trailingContent = { value?.let { Text(it) } },
        colors = colors(containerColor = Transparent)
    )
    if (showDivider) Divider()
}

@Composable
private fun Divider() = HorizontalDivider(
    Modifier
        .padding(horizontal = PaddingL.toDp())
        .testTag(stringResource(divider_tag))
)

@Composable
private fun Modifier.iconTag() = testTag(stringResource(R.string.icon_tag))
