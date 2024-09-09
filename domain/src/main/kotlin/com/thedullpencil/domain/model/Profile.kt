package com.thedullpencil.domain.model

import com.thedullpencil.core.util.MistriappDate

data class Profile(
    val userId: String,
    val name: String,
    val currentDate: MistriappDate,
    val currentYear: Int
)