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
    @MethodSource("prevDays")
    fun `getPreviousDay gets previous day`(input: Day, expected: Day) {
        assertEquals(expected, input.getPreviousDay())
    }

    @ParameterizedTest(name = "next for {0}")
    @MethodSource("nextDays")
    fun `getNextDay gets next day`(input: Day, expected: Day) {
        assertEquals(expected, input.getNextDay())
    }

    @ParameterizedTest(name = "{0} to string")
    @MethodSource("dayStrings")
    fun `day strings are properly formatted`(input: Day, expected: String) {
        assertEquals(expected, input.toDateString())
    }

    @ParameterizedTest(name = "previous for {0}")
    @MethodSource("prevDates")
    fun `getPreviousDate gets previous date`(input: MistriappDate, expected: MistriappDate) {
        assertEquals(expected, input.getPreviousDate())
    }

    @ParameterizedTest(name = "next for {0}")
    @MethodSource("nextDates")
    fun `getNextDate gets next date`(input: MistriappDate, expected: MistriappDate) {
        assertEquals(expected, input.getNextDate())
    }

    @ParameterizedTest(name = "{0} to string")
    @MethodSource("dateStrings")
    fun `date strings are properly formatted`(input: MistriappDate, expected: String) {
        assertEquals(expected, input.toDateString())
    }

    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("seasonListCases")
    fun `toSeasonList resolves correctly`(input: List<String>?, expected: List<Season>) {
        assertEquals(expected, input.toSeasonList())
    }

    companion object {
        private val standardDay = Day(Summer, 12)
        private val firstDayYear = Day(Spring, FIRST_DAY)
        private val firstDayMonth = Day(Fall, FIRST_DAY)
        private val lastDayYear = Day(Winter, LAST_DAY)
        private val lastDayMonth = Day(Fall, LAST_DAY)

        @JvmStatic
        fun prevDays() = listOf(
            of(standardDay, Day(Summer, 11)),
            of(firstDayYear, Day(Winter, 28)),
            of(firstDayMonth, Day(Summer, 28)),
            of(lastDayYear, Day(Winter, 27)),
            of(lastDayMonth, Day(Fall, 27)),
        )

        @JvmStatic
        fun nextDays() = listOf(
            of(standardDay, Day(Summer, 13)),
            of(firstDayYear, Day(Spring, 2)),
            of(firstDayMonth, Day(Fall, 2)),
            of(lastDayYear, Day(Spring, 1)),
            of(lastDayMonth, Day(Winter, 1)),
        )

        @JvmStatic
        fun dayStrings() = listOf(
            of(standardDay, "Summer 12"),
            of(firstDayYear, "Spring 1"),
            of(firstDayMonth, "Fall 1"),
            of(lastDayYear, "Winter 28"),
            of(lastDayMonth, "Fall 28"),
        )

        private val standardDate = MistriappDate(Day(Summer, 12), 3)
        private val firstDateYear1 = MistriappDate(Day(Spring, FIRST_DAY), 1)
        private val firstDateYear2 = MistriappDate(Day(Spring, FIRST_DAY), 2)
        private val firstDateMonth = MistriappDate(Day(Fall, FIRST_DAY), 1)
        private val lastDateYear1 = MistriappDate(Day(Winter, LAST_DAY), 1)
        private val lastDateMonth = MistriappDate(Day(Fall, LAST_DAY), 1)

        @JvmStatic
        fun prevDates() = listOf(
            of(standardDate, MistriappDate(Day(Summer, 11), 3)),
            of(firstDateYear1, MistriappDate(Day(Spring, FIRST_DAY), 1)),
            of(firstDateYear2, MistriappDate(Day(Winter, LAST_DAY), 1)),
            of(firstDateMonth, MistriappDate(Day(Summer, LAST_DAY), 1)),
            of(lastDateYear1, MistriappDate(Day(Winter, 27), 1)),
            of(lastDateMonth, MistriappDate(Day(Fall, 27), 1)),
        )

        @JvmStatic
        fun nextDates() = listOf(
            of(standardDate, MistriappDate(Day(Summer, 13), 3)),
            of(firstDateYear1, MistriappDate(Day(Spring, 2), 1)),
            of(firstDateYear2, MistriappDate(Day(Spring, 2), 2)),
            of(firstDateMonth, MistriappDate(Day(Fall, 2), 1)),
            of(lastDateYear1, MistriappDate(Day(Spring, 1), 2)),
            of(lastDateMonth, MistriappDate(Day(Winter, 1), 1)),
        )

        @JvmStatic
        fun dateStrings() = listOf(
            of(standardDate, "Summer 12, Year 3"),
            of(firstDateYear1, "Spring 1, Year 1"),
            of(firstDateYear2, "Spring 1, Year 2"),
            of(firstDateMonth, "Fall 1, Year 1"),
            of(lastDateYear1, "Winter 28, Year 1"),
            of(lastDateMonth, "Fall 28, Year 1"),
        )

        @JvmStatic
        fun seasonListCases() = listOf(
            of(null, Season.entries),
            of(listOf("spring"), listOf(Spring)),
            of(listOf("summer", "fall"), listOf(Summer, Fall)),
            of(listOf("winter", "summer"), listOf(Winter, Summer)),
            of(emptyList<String>(), emptyList<Season>()),
        )
    }
}
