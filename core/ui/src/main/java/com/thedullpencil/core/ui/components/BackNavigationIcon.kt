package com.thedullpencil.core.ui.components

import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.R.string.core_ui_back_content_description

@Composable
fun BackNavigationIcon(onClick: () -> Unit) = IconButton(onClick = onClick) {
    Icon(
        Filled.ArrowBack,
        contentDescription = stringResource(core_ui_back_content_description),
    )
}
