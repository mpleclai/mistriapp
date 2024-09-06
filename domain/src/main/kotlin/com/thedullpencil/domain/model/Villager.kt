package com.thedullpencil.domain.model

data class Villager(
    val name: String,
//    val birthday: Pair<Season, Int>,
)


enum class Season { Spring, Summer, Fall, Winter }
