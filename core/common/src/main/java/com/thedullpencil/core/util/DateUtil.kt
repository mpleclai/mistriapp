package com.thedullpencil.core.util

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.core.util.Season.Winter

typealias Day = Pair<Season, Int>
data class MistriappDate(val day: Day, val year: Int) {
    val isAtCalendarStart: Boolean get() = day.first == Spring && day.second == FIRST_DAY && year == 1
    val isAtYearStart: Boolean get() = day.first == Spring && day.second == FIRST_DAY
    val isAtYearEnd: Boolean get() = day.first == Winter && day.second == LAST_DAY
}

enum class Season {
    Spring, Summer, Fall, Winter;

    val next: Season get() = when (this) {
        Spring -> Summer
        Summer -> Fall
        Fall -> Winter
        Winter -> Spring
    }

    val previous: Season get() = when (this) {
        Spring -> Winter
        Summer -> Spring
        Fall -> Summer
        Winter -> Fall
    }
}

const val FIRST_DAY = 1
const val LAST_DAY = 28

private const val SEASON_SPRING = "spring"
private const val SEASON_SUMMER = "summer"
private const val SEASON_FALL = "fall"
private const val SEASON_WINTER = "winter"

fun List<String>?.toSeasonList(): List<Season> {
    if (this == null) return Season.entries
    return mapNotNull { value ->
        when (value.lowercase()) {
            SEASON_SPRING -> Spring
            SEASON_SUMMER -> Summer
            SEASON_FALL -> Fall
            SEASON_WINTER -> Winter
            else -> null
        }
    }
}

fun toDay(season: String, day: Int): Day? = when (season.lowercase()) {
    SEASON_SPRING -> Day(Spring, day)
    SEASON_SUMMER -> Day(Summer, day)
    SEASON_FALL -> Day(Fall, day)
    SEASON_WINTER -> Day(Winter, day)
    else -> null
}

fun Day.getPreviousDay(): Day =
    if (second.isNotFirstDayOfMonth()) Day(first, second - 1) else Day(first.previous, LAST_DAY)

fun Day.getNextDay(): Day =
    if (second.isNotLastDayOfMonth()) Day(first, second + 1) else Day(first.next, FIRST_DAY)

fun Day.toDateString(): String = "${this.first} ${this.second}"

fun MistriappDate.getPreviousDate(): MistriappDate = when {
    isAtCalendarStart -> this
    isAtYearStart -> MistriappDate(day.getPreviousDay(), year - 1)
    else -> MistriappDate(day.getPreviousDay(), year)
}

fun MistriappDate.getNextDate(): MistriappDate = when {
    isAtYearEnd -> MistriappDate(day.getNextDay(), year + 1)
    else -> MistriappDate(day.getNextDay(), year)
}

fun MistriappDate.toDateString(): String = "${this.day.first} ${this.day.second}" + ", Year $year"

fun Int.isNotLastDayOfMonth(): Boolean = this != LAST_DAY
fun Int.isNotFirstDayOfMonth(): Boolean = this != FIRST_DAY
