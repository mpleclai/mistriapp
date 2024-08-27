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
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.mistriapp.navigation.AppNavHost
import com.thedullpencil.mistriapp.navigation.TopLevelDestination

@Composable
fun Mistriapp(
    appState: AppState,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) = Mistriapp(appState, windowAdaptiveInfo) {}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Mistriapp(
    appState: AppState,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    onTopAppBarActionClick: () -> Unit,
) {
    val currentDestination = appState.currentDestination
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    CustomNavigationSuiteScaffold(
        windowAdaptiveInfo = windowAdaptiveInfo,
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
                item(selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = { Icon(destination.icon, null) },
                    label = { Text(stringResource(destination.title)) }
                )
            }
        }
    ) { ScaffoldContent(appState, scrollBehavior, onTopAppBarActionClick) }
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
        containerColor = Color.Transparent,
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
private fun CustomNavigationSuiteScaffold(
    windowAdaptiveInfo: WindowAdaptiveInfo,
    navigationSuiteItems: MistriappNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            MistriappNavigationSuiteScope(this).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        modifier = modifier,
    ) { content() }
}

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

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
class MistriappNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = { icon() },
        label = label,
        modifier = modifier,
    )
}