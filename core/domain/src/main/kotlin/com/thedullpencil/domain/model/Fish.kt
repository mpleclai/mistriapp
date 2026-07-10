package com.thedullpencil.domain.model

import com.thedullpencil.core.util.Season

data class Fish(
    val name: String,
    val description: String,
    val price: String,
    val location: String,
    val size: String,
    val season: List<Season>,
    val weather: String,
    val rarity: String,
    val museum: String,
    val diveable: String
)
