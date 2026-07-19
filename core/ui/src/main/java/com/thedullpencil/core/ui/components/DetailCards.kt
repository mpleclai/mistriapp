package com.thedullpencil.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import com.thedullpencil.core.ui.theme.Dimens.PaddingM
import com.thedullpencil.core.ui.theme.toDp

@Composable
fun DetailListCard(header: String, items: List<InfoItem>) {
    Card(Modifier.fillMaxWidth()) {
        Text(header, Modifier.padding(PaddingM.toDp()), style = typography.titleLarge)
        Column { items.forEach { it.ToListItem() } }
    }
}

@Composable
fun DetailInfoCard(title: String, trailingContent: @Composable () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        ListItem(
            headlineContent = { Text(title, style = typography.titleLarge) },
            trailingContent = {
                CompositionLocalProvider(
                    LocalTextStyle provides typography.bodyLarge,
                    LocalContentColor provides colorScheme.onSurface,
                ) { trailingContent() }
            },
            colors = ListItemDefaults.colors(containerColor = Transparent),
        )
    }
}
