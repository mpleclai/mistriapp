package com.thedullpencil.mistriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thedullpencil.common.ui.theme.MistriappTheme
import com.thedullpencil.mistriapp.ui.Mistriapp
import com.thedullpencil.mistriapp.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            MistriappTheme {
                Mistriapp(appState)
            }
        }
    }
}