package com.thedullpencil.mistriapp.ui

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.thedullpencil.core.navigation.TopLevelRoute.Fishing
import com.thedullpencil.core.navigation.TopLevelRoute.Home
import com.thedullpencil.core.navigation.TopLevelRoute.Museum
import com.thedullpencil.core.navigation.TopLevelRoute.Villagers
import com.thedullpencil.core.ui.R.string.core_ui_app_name
import com.thedullpencil.core.ui.theme.Dimens.PaddingL
import com.thedullpencil.core.ui.theme.toDp
import com.thedullpencil.mistriapp.navigation.AppNavHost
import com.thedullpencil.mistriapp.navigation.TopLevelDestination
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Fishing
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Home
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Museum
import com.thedullpencil.mistriapp.navigation.TopLevelDestination.Villagers
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Mistriapp(
    appState: AppState,
//    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val onNavClick: () -> Unit = {
        scope.launch { drawerState.apply { if (isClosed) open() else close() } }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MistriappDrawerSheet(
                appState = appState,
                onDestinationClick = { scope.launch { drawerState.close() } },
            )
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
                TopAppBar(
                    title = stringResource(destination.title),
                    scrollBehavior = scrollBehavior,
                    onNavClick = { onTopAppBarActionClick() },
                )
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

@Composable
private fun MistriappDrawerSheet(appState: AppState, onDestinationClick: () -> Unit) {
    val currentDestination = appState.currentDestination
    ModalDrawerSheet {
        DrawerTitle(stringResource(core_ui_app_name))
        appState.topLevelDestinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationDrawerItem(
                label = { Text(stringResource(destination.title)) },
                icon = { Icon(destination.icon, null) },
                selected = selected,
                onClick = {
                    appState.navigateToTopLevelDestination(destination)
                    onDestinationClick()
                },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(dest: TopLevelDestination): Boolean =
    this?.hierarchy?.any { entry ->
        when (dest) {
            Home -> entry.hasRoute<Home>()
            Villagers -> entry.hasRoute<Villagers>()
            Museum -> entry.hasRoute<Museum>()
            Fishing -> entry.hasRoute<Fishing>()
        }
    } ?: false

@Composable
private fun DrawerTitle(text: String) =
    Text(text, modifier = Modifier.padding(PaddingL.toDp()), style = typography.titleLarge)
