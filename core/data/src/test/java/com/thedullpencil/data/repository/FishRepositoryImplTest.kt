package com.thedullpencil.data.repository

import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FishRepositoryImplTest {
    private val repository = FishRepositoryImpl()

    @Test
    fun `getFishData returns null when fish name is missing`() = runTest {
        val result = repository.getFishData("not-a-real-fish")

        assertNull(result)
    }

    @Test
    fun `getFishData returns data when fish name exists`() = runTest {
        val result = repository.getFishData("Anchovy")

        assertNotNull(result)
    }
}

