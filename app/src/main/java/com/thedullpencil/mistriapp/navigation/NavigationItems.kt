package com.thedullpencil.mistriapp.navigation

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.thedullpencil.common.R.string.common_nav_home
import com.thedullpencil.common.R.string.common_nav_museum
import com.thedullpencil.common.R.string.common_nav_villagers
import com.thedullpencil.common.R.string.common_nav_fishing

enum class TopLevelDestination(val title: Int, val icon: ImageVector) {
    Home(common_nav_home, Filled.DateRange),
    Villagers(common_nav_villagers, Filled.Face),
    Museum(common_nav_museum, Filled.AccountBox),
    Fishing(common_nav_fishing, Filled.Warning)
}