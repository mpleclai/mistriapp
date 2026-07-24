package com.thedullpencil.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thedullpencil.core.ui.R.string.core_ui_unable_to_load_data

@Composable
fun EmptyListMessage(modifier: Modifier = Modifier) =
    Box(modifier.fillMaxSize(), contentAlignment = Center) {
        Text(stringResource(core_ui_unable_to_load_data))
    }
