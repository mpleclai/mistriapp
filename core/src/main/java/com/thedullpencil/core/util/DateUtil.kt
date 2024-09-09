package com.thedullpencil.core.util

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.core.util.Season.Winter

typealias MistriappDate = Pair<Season, Int>

enum class Season { Spring, Summer, Fall, Winter }

fun MistriappDate.getPreviousDay(): MistriappDate = if (second.notFirstDayOfMonth()) {
    MistriappDate(first, second - 1)
} else {
    MistriappDate(first.getPreviousSeason(), LAST_DAY)
}

fun MistriappDate.getNextDay(): MistriappDate = if (second.notLastDayOfMonth()) {
    MistriappDate(first, second + 1)
} else {
    MistriappDate(first.getNextSeason(), FIRST_DAY)
}

fun Season.getNextSeason(): Season = when (this) {
    Spring -> Summer
    Summer -> Fall
    Fall -> Winter
    Winter -> Spring
}

fun Season.getPreviousSeason(): Season = when (this) {
    Spring -> Winter
    Summer -> Spring
    Fall -> Summer
    Winter -> Fall
}

fun Int.notLastDayOfMonth(): Boolean = this != LAST_DAY
fun Int.notFirstDayOfMonth(): Boolean = this != FIRST_DAY

private const val FIRST_DAY = 1
private const val LAST_DAY = 28

fun MistriappDate.toDateString(): String = "${this.first} ${this.second}"

fun getMistriappDate(string: String, day: Int): MistriappDate? = when (string) {
    "Spring" -> MistriappDate(Spring, day)
    "Summer" -> MistriappDate(Summer, day)
    "Fall" -> MistriappDate(Fall, day)
    "Winter" -> MistriappDate(Winter, day)
    else -> null
}

