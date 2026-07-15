package com.thedullpencil.domain.model

import com.thedullpencil.core.util.Season

data class Fish(
    val name: String,
    val item: String,
    val seasons: List<Season>,
    val waterType: List<String>,
    val weather: List<String>,
    val locations: List<String>,
    val size: String,
    val legendary: Boolean,
    val rarity: String,
    val retrieval: List<String>,
    val isChest: Boolean,
    val perkArtifact: String?,
    val hasPerk: String?,
    val baitOnly: Boolean,
)
