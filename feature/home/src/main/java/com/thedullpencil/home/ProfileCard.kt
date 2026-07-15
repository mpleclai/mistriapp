package com.thedullpencil.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thedullpencil.core.ui.components.InfoItem
import com.thedullpencil.core.ui.components.ToListItem

@Composable
fun ProfileCard(profileName: String) = Card(Modifier.fillMaxWidth()) {
    InfoItem(name = profileName, icon = Filled.AccountCircle).ToListItem(false)
}
