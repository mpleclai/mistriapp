package com.thedullpencil.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data class VillagerDetail(val villagerName: String) {
    companion object {
        const val NAME_ARG = "villagerName"
    }
}
//@Serializable data class MuseumDetail(val itemId: String) {}

@Serializable
data class FishingDetail(val fishName: String) {
    companion object {
        const val NAME_ARG = "fishName"
    }
}
