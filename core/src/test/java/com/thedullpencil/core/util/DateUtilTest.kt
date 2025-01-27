package com.thedullpencil.core.util

import com.thedullpencil.core.util.Season.Fall
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.Season.Summer
import com.thedullpencil.core.util.Season.Winter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource

class DateUtilTest {
    @ParameterizedTest(name = "previous for {0}")
    @MethodSource("prevDates")
    fun `getPreviousDay gets previous day`(input: Day, expected: Day) {
        assertEquals(expected, input.getPreviousDay())
    }

    @ParameterizedTest(name = "next for {0}")
    @MethodSource("nextDates")
    fun `getNextDay gets next day`(input: Day, expected: Day) {
        assertEquals(expected, input.getNextDay())
    }

    @ParameterizedTest(name = "{0} to string")
    @MethodSource("dateStrings")
    fun `strings are properly formatted`(input: Day, expected: String) {
        assertEquals(expected, input.toDateString())
    }

    companion object {
        private val standardDay = Day(Summer, 12)
        private val firstDayYear = Day(Spring, FIRST_DAY)
        private val firstDayMonth = Day(Fall, FIRST_DAY)
        private val lastDayYear = Day(Winter, LAST_DAY)
        private val lastDayMonth = Day(Fall, LAST_DAY)

        @JvmStatic
        fun prevDates() = listOf(
            of(standardDay, Day(Summer, 11)),
            of(firstDayYear, Day(Winter, 28)),
            of(firstDayMonth, Day(Summer, 28)),
            of(lastDayYear, Day(Winter, 27)),
            of(lastDayMonth, Day(Fall, 27)),
        )

        @JvmStatic
        fun nextDates() = listOf(
            of(standardDay, Day(Summer, 13)),
            of(firstDayYear, Day(Spring, 2)),
            of(firstDayMonth, Day(Fall, 2)),
            of(lastDayYear, Day(Spring, 1)),
            of(lastDayMonth, Day(Winter, 1)),
        )

        @JvmStatic
        fun dateStrings() = listOf(
            of(standardDay, "Summer 12"),
            of(firstDayYear, "Spring 1"),
            of(firstDayMonth, "Fall 1"),
            of(lastDayYear, "Winter 28"),
            of(lastDayMonth, "Fall 28"),
        )
    }
}