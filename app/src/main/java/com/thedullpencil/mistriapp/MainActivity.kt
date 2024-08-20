package com.thedullpencil.mistriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thedullpencil.common.ui.theme.Dimens.PaddingL
import com.thedullpencil.common.ui.theme.MistriappTheme
import com.thedullpencil.common.ui.theme.toDp
import com.thedullpencil.mistriapp.navigation.AppNavHost
import com.thedullpencil.mistriapp.navigation.NavigationItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MistriappTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = Closed)
    val scope = rememberCoroutineScope()
    val onNavClick = {
        scope.launch {
            drawerState.apply { if (isClosed) open() else close() }
        }
    }
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = { ModalDrawerSheet(navController, drawerState, scope) },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBar(scrollBehavior) { onNavClick() } }
        ) { innerPadding ->
            AppNavHost(navController, Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun ModalDrawerSheet(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) = ModalDrawerSheet {
    DrawerTitle("Drawer Title")
    HorizontalDivider()
    for (navItem in NavigationItems.entries) {
        NavigationDrawerItem(
            label = { Text(stringResource(navItem.title)) },
            selected = false,
            onClick = {
                navController.navigate(navItem.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(navItem.icon, contentDescription = null) }
        )
    }
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
    title = { Text("Stardew Helper", maxLines = 1, overflow = Ellipsis) },
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
