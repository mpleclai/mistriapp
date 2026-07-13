package com.thedullpencil.mistriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.thedullpencil.core.ui.theme.AppTypography
import com.thedullpencil.core.ui.theme.MistriappTheme
import com.thedullpencil.core.ui.theme.highContrastDarkColorScheme
import com.thedullpencil.core.ui.theme.highContrastLightColorScheme
import com.thedullpencil.core.ui.theme.mediumContrastDarkColorScheme
import com.thedullpencil.core.ui.theme.mediumContrastLightColorScheme
import com.thedullpencil.home.HomeScreenContent
import com.thedullpencil.home.HomeUiState
import com.thedullpencil.home.HomeUiState.HomeInfo
import com.thedullpencil.mistriapp.ui.Mistriapp
import com.thedullpencil.mistriapp.ui.TopAppBar
import com.thedullpencil.mistriapp.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            MistriappTheme(dynamicColor = false) {
                Mistriapp(appState)
            }
        }
    }
}

private enum class ContrastLevel { STANDARD, MEDIUM, HIGH }

private data class ThemePreviewConfig(
    val darkTheme: Boolean,
    val contrastLevel: ContrastLevel,
    val dynamicColor: Boolean,
)

private class ThemePreviewParameterProvider : PreviewParameterProvider<ThemePreviewConfig> {
    override val values = sequence {
        for (dark in listOf(false, true)) {
            for (dynamic in listOf(false, true)) {
                yield(ThemePreviewConfig(dark, ContrastLevel.STANDARD, dynamic))
            }
            yield(ThemePreviewConfig(dark, ContrastLevel.MEDIUM, false))
            yield(ThemePreviewConfig(dark, ContrastLevel.HIGH, false))
        }
    }
}

@Composable
private fun PreviewTheme(config: ThemePreviewConfig, content: @Composable () -> Unit) {
    when (config.contrastLevel) {
        ContrastLevel.STANDARD -> MistriappTheme(
            darkTheme = config.darkTheme,
            dynamicColor = config.dynamicColor,
            content = content,
        )
        ContrastLevel.MEDIUM -> MaterialTheme(
            colorScheme = if (config.darkTheme) mediumContrastDarkColorScheme else mediumContrastLightColorScheme,
            typography = AppTypography,
            content = content,
        )
        ContrastLevel.HIGH -> MaterialTheme(
            colorScheme = if (config.darkTheme) highContrastDarkColorScheme else highContrastLightColorScheme,
            typography = AppTypography,
            content = content,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewDynamicColors
@Preview(heightDp = 600)
@Composable
private fun MyCustomAppPreview(
    @PreviewParameter(ThemePreviewParameterProvider::class) config: ThemePreviewConfig
) {
    PreviewTheme(config) {
        val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            TopAppBar(
                title = "Daily Reminders",
                scrollBehavior = scrollBehavior,
                onNavClick = {},
            )
            HomeScreenContent(uiState = HomeUiState.Empty)
        }
    }
}