package com.thedullpencil.core.util

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.core.util.Season.Winter

typealias Day = Pair<Season, Int>
data class MistriappDate(val day: Day, val year: Int)

enum class Season { Spring, Summer, Fall, Winter }

fun Day.getPreviousDay(): Day = if (second.notFirstDayOfMonth()) {
    Day(first, second - 1)
} else {
    Day(first.getPreviousSeason(), LAST_DAY)
}

fun Day.getNextDay(): Day = if (second.notLastDayOfMonth()) {
    Day(first, second + 1)
} else {
    Day(first.getNextSeason(), FIRST_DAY)
}

fun MistriappDate.getPreviousDate() : MistriappDate = with(day) {
    if (first == Spring && second == FIRST_DAY && year == 1) {
        MistriappDate(this, year)
    } else if (first == Spring && second == FIRST_DAY) {
        MistriappDate(getPreviousDay(), year - 1)
    } else {
        MistriappDate(getPreviousDay(), year)
    }
}

fun MistriappDate.getNextDate(): MistriappDate = with(day) {
    if (first == Winter && second == LAST_DAY) {
        MistriappDate(getNextDay(), year + 1)
    } else {
        MistriappDate(getNextDay(), year)
    }
}

fun Day.toDateString(): String = "${this.first} ${this.second}"

fun MistriappDate.toDateString(): String = "${this.day.first} ${this.day.second}" + ", Year $year"

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

const val FIRST_DAY = 1
const val LAST_DAY = 28

fun getMistriappDate(string: String, day: Int): Day? = when (string) {
    "Spring" -> Day(Spring, day)
    "Summer" -> Day(Summer, day)
    "Fall" -> Day(Fall, day)
    "Winter" -> Day(Winter, day)
    else -> null
}

fun String.toSeason(): List<Season> {
    val normalized = replace("\\u00a0", " ").replace('\u00A0', ' ').trim()
    if (normalized == "All") {
        return Season.entries
    }

    val seasons = normalized
        .split(Regex("\\s+"))
        .mapNotNull { value ->
            when (value) {
                "Spring" -> Spring
                "Summer" -> Summer
                "Fall" -> Fall
                "Winter" -> Winter
                else -> null
            }
        }
        .distinct()

    require(seasons.isNotEmpty()) {
        "Unknown season value: '$this' (normalized: '$normalized')"
    }
    return seasons
}