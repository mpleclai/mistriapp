package com.thedullpencil.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VillagerData(
    @SerialName("name") val name: String,
    @SerialName("birthday_season") val birthdaySeason: String,
    @SerialName("birthday_day") val birthdayDay: Int,
    @SerialName("job") val job: String,
    @SerialName("dateable") val dateable: Boolean = false,
    @SerialName("loved_gifts") val lovedGifts: List<String> = emptyList(),
    @SerialName("liked_gifts") val likedGifts: List<String> = emptyList(),
    @SerialName("hated_gift") val hatedGift: String? = null,
    @SerialName("disliked_gift_tags") val dislikedGiftTags: List<String> = emptyList(),
)
