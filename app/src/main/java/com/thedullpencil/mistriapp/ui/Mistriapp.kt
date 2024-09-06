package com.thedullpencil.mistriapp.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Horizontal
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.mistriapp.navigation.AppNavHost
import com.thedullpencil.mistriapp.navigation.TopLevelDestination
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Mistriapp(
    appState: AppState,
//    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val currentDestination = appState.currentDestination
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val onNavClick: () -> Unit = {
        scope.launch { drawerState.apply { if (isClosed) open() else close() } }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerTitle("Drawer Title")
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
                    NavigationDrawerItem(
                        label = { Text(stringResource(destination.title)) },
                        icon = { Icon(destination.icon, null) },
                        selected = selected,
                        onClick = {
                            appState.navigateToTopLevelDestination(destination)
                            scope.launch { drawerState.close() }
                        },
                    )
                }
            }
        }
    ) { ScaffoldContent(appState, scrollBehavior, onNavClick) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScaffoldContent(
    appState: AppState,
    scrollBehavior: TopAppBarScrollBehavior,
    onTopAppBarActionClick: () -> Unit
) {
    val destination = appState.currentTopLevelDestination
    val shouldShowTopAppBar = destination != null
    Scaffold(
        contentColor = colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(WindowInsets.safeDrawing.only(Horizontal)),
        ) {
            if (shouldShowTopAppBar) {
                TopAppBar(scrollBehavior) { onTopAppBarActionClick() }
            }
            Box(
                // Workaround for https://issuetracker.google.com/338478720
                modifier = Modifier.consumeWindowInsets(
                    if (shouldShowTopAppBar) {
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    } else {
                        WindowInsets(0, 0, 0, 0)
                    },
                ),
            ) { AppNavHost(appState = appState) }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any { it.route?.contains(destination.name, true) ?: false } ?: false

@Composable
private fun DrawerTitle(text: String) =
    Text(text, modifier = Modifier.padding(PaddingL.toDp()), style = typography.titleLarge)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavClick: () -> Unit
) = CenterAlignedTopAppBar(
    title = { Text("Mistria Helper", maxLines = 1, overflow = Ellipsis) },
    navigationIcon = {
        IconButton(onNavClick) {
            Icon(Filled.Menu, contentDescription = "Localized description")
        }
    },
    actions = {
        IconButton(onClick = {/* do something */ }) {
            Icon(Filled.AccountCircle, contentDescription = "Localized description")
        }
    },
    colors = topAppBarColors(
        containerColor = colorScheme.primaryContainer,
        titleContentColor = colorScheme.primary
    ),
    scrollBehavior = scrollBehavior,
)
