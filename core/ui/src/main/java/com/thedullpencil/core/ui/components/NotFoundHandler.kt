package com.thedullpencil.core.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun NotFoundHandler(message: String, onBackClick: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        onBackClick()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
