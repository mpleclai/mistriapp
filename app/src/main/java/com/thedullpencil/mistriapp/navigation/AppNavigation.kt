package com.thedullpencil.mistriapp.navigation

import com.thedullpencil.mistriapp.navigation.Screen.HOME
import com.thedullpencil.mistriapp.navigation.Screen.VILLAGERS

enum class Screen {
    HOME,
    VILLAGERS
}

sealed class NavigationItem(val route: String){
    data object Home : NavigationItem(HOME.name)
    data object Villagers: NavigationItem(VILLAGERS.name)
}
