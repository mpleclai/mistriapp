package com.thedullpencil.data.repository

import androidx.test.core.app.ApplicationProvider
import com.thedullpencil.data.util.JsonResourceLoader
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FishRepositoryImplTest {
    private val loader = JsonResourceLoader(ApplicationProvider.getApplicationContext())
    private val repository = FishRepositoryImpl(loader)

    @Test
    fun `getFishData returns null when fish name is missing`() = runBlocking {
        val result = repository.getFishData("not-a-real-fish")

        assertNull(result)
    }

    @Test
    fun `getFishData returns data when fish name exists`() = runBlocking {
        val result = repository.getFishData("anchovy")

        assertNotNull(result)
    }
}

