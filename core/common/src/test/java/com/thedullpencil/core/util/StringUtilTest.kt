package com.thedullpencil.core.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource

class StringUtilTest {
    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("displayNameCases")
    fun `toDisplayName transforms correctly`(input: String, expected: String) {
        assertEquals(expected, input.toDisplayName())
    }

    companion object {
        @JvmStatic
        fun displayNameCases() = listOf(
            of("bass", "Bass"),
            of("treasure_box_wood", "Treasure Box Wood"),
            of("basic_wood", "Basic Wood"),
            of("smallmouth_bass", "Smallmouth Bass"),
            of("unidentified_artifact", "Unidentified Artifact"),
        )
    }
}
