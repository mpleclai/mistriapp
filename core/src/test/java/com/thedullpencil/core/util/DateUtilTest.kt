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
    fun `getPreviousDay gets previous day`(input: MistriappDate, expected: MistriappDate) {
        assertEquals(expected, input.getPreviousDay())
    }

    @ParameterizedTest(name = "next for {0}")
    @MethodSource("nextDates")
    fun `getNextDay gets next day`(input: MistriappDate, expected: MistriappDate) {
        assertEquals(expected, input.getNextDay())
    }

    @ParameterizedTest(name = "{0} to string")
    @MethodSource("dateStrings")
    fun `strings are properly formatted`(input: MistriappDate, expected: String) {
        assertEquals(expected, input.toDateString())
    }

    companion object {
        private val standardDay = MistriappDate(Summer, 12)
        private val firstDayYear = MistriappDate(Spring, FIRST_DAY)
        private val firstDayMonth = MistriappDate(Fall, FIRST_DAY)
        private val lastDayYear = MistriappDate(Winter, LAST_DAY)
        private val lastDayMonth = MistriappDate(Fall, LAST_DAY)

        @JvmStatic
        fun prevDates() = listOf(
            of(standardDay, MistriappDate(Summer, 11)),
            of(firstDayYear, MistriappDate(Winter, 28)),
            of(firstDayMonth, MistriappDate(Summer, 28)),
            of(lastDayYear, MistriappDate(Winter, 27)),
            of(lastDayMonth, MistriappDate(Fall, 27)),
        )

        @JvmStatic
        fun nextDates() = listOf(
            of(standardDay, MistriappDate(Summer, 13)),
            of(firstDayYear, MistriappDate(Spring, 2)),
            of(firstDayMonth, MistriappDate(Fall, 2)),
            of(lastDayYear, MistriappDate(Spring, 1)),
            of(lastDayMonth, MistriappDate(Winter, 1)),
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