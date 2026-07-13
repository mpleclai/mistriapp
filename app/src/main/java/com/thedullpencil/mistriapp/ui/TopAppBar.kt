package com.thedullpencil.mistriapp.ui

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import com.thedullpencil.core.ui.R.string.core_ui_account_description
import com.thedullpencil.core.ui.R.string.core_ui_nav_menu_description

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavClick: () -> Unit,
    onProfileClick: () -> Unit = {},
) = CenterAlignedTopAppBar(
    title = { Text(title, maxLines = 1, overflow = Ellipsis, style = typography.headlineMedium) },
    navigationIcon = {
        IconButton(onNavClick) {
            Icon(Filled.Menu, contentDescription = stringResource(core_ui_nav_menu_description))
        }
    },
    actions = {
        IconButton(onClick = onProfileClick) {
            Icon(
                Filled.AccountCircle,
                contentDescription = stringResource(core_ui_account_description)
            )
        }
    },
    colors = topAppBarColors(
        containerColor = colorScheme.primaryContainer,
        titleContentColor = colorScheme.primary
    ),
    scrollBehavior = scrollBehavior,
)
