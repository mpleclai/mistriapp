package com.thedullpencil.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FishData (
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String,
    @SerialName("Price") val price: String,
    @SerialName("Location") val location: String,
    @SerialName("Size") val size: String,
    @SerialName("Season") val season: String,
    @SerialName("Weather") val weather: String,
    @SerialName("Rarity") val rarity: String,
    @SerialName("Museum") val museum: String,
    @SerialName("Diveable") val diveable: String,
)
