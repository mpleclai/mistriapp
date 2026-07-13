package com.thedullpencil.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class TopLevelRoute {
    @Serializable data object Home : TopLevelRoute()
    @Serializable data class Villagers(val villagerName: String? = null) : TopLevelRoute()
    @Serializable data object Museum : TopLevelRoute()
    @Serializable data object Fishing : TopLevelRoute()
}
