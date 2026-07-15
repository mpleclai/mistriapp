package com.thedullpencil.domain.model

import com.thedullpencil.core.util.Day

data class Villager(
    val name: String,
    val birthday: Day,
    val job: String? = null,
    val dateable: Boolean = false,
    val lovedGifts: List<String> = emptyList(),
    val likedGifts: List<String> = emptyList(),
    val hatedGift: String? = null,
    val dislikedGiftTags: List<String> = emptyList(),
)
