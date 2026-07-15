package com.thedullpencil.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FishData(
    @SerialName("name") val name: String,
    @SerialName("item") val item: String,
    @SerialName("seasons") val seasons: List<String>? = null,
    @SerialName("water_type") val waterType: List<String>,
    @SerialName("weather") val weather: List<String>? = null,
    @SerialName("locations") val locations: List<String>? = null,
    @SerialName("size") val size: String,
    @SerialName("any_size") val anySize: Boolean = false,
    @SerialName("legendary") val legendary: Boolean = false,
    @SerialName("rarity") val rarity: String,
    @SerialName("retrieval") val retrieval: List<String>,
    @SerialName("is_chest") val isChest: Boolean = false,
    @SerialName("recipe") val recipe: Boolean = false,
    @SerialName("perk_artifact") val perkArtifact: String? = null,
    @SerialName("has_perk") val hasPerk: String? = null,
    @SerialName("bait_only") val baitOnly: Boolean = false,
)
