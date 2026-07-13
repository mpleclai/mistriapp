package com.thedullpencil.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.components.InfoBlock
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.feature.home.R.string.feature_home_empty
import com.thedullpencil.feature.home.R.string.feature_home_reminders

@Composable
fun RemindersBlock() = InfoBlock(
    modifier = Modifier.fillMaxHeight(),
    header = stringResource(id = feature_home_reminders),
    items = listOf(InfoItem(name = stringResource(id = feature_home_empty)))
)
