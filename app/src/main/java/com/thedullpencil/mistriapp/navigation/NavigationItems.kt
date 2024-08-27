package com.thedullpencil.mistriapp.navigation

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import com.thedullpencil.common.R.string.nav_home
import com.thedullpencil.common.R.string.nav_villagers

enum class TopLevelDestination(val title: Int, val icon: ImageVector) {
    Home(nav_home, Filled.DateRange),
    Villagers(nav_villagers, Filled.Face)
}