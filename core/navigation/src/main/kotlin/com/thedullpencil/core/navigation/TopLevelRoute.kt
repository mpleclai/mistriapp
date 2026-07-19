package com.thedullpencil.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class TopLevelRoute {
    @Serializable data object Home : TopLevelRoute()
    @Serializable data object Villagers : TopLevelRoute()
    @Serializable data object Museum : TopLevelRoute()
    @Serializable data object Fishing : TopLevelRoute()
}
