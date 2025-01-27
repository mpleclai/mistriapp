package com.thedullpencil.domain.model

import com.thedullpencil.core.util.Day

data class Profile(
    val userId: String,
    val name: String,
    val currentDate: Day,
    val currentYear: Int
)