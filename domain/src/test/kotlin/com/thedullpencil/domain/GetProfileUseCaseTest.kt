package com.thedullpencil.domain

import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.data.model.ProfileData
import com.thedullpencil.data.repository.ProfileRepository
import com.thedullpencil.domain.model.Profile
import com.thedullpencil.domain.usecase.GetProfileUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

private const val ID_1 = "1"
private const val NAME = "name"
private const val ID_STRING = "id"
private const val YEAR = 1
private const val DAY = 4
private const val BAD_SEASON = "fake"
private const val BAD_YEAR = -10
private const val BAD_DAY = -1

class GetProfileUseCaseTest {

    private val testProfileData = ProfileData(ID_1, NAME, Spring.name, DAY, YEAR)
    private val testProfileDataEdgeCase =
        ProfileData(ID_STRING, NAME, BAD_SEASON, BAD_DAY, BAD_YEAR)

    private val expectedProfile = Profile(ID_1, NAME, MistriappDate(Spring, DAY), YEAR)
    private val expectedProfileEdgeCase =
        Profile(ID_STRING, NAME, MistriappDate(Spring, YEAR), BAD_YEAR)

    private val profileRepository = mockk<ProfileRepository> {
        coEvery { this@mockk.getProfileData(any()) } returns testProfileData
    }

    private val getProfileUseCase by lazy { GetProfileUseCase(profileRepository) }

    @Test
    fun `profile with expected data returned with id 1`() = runTest {
        val profile = getProfileUseCase.invoke(ID_1)
        with(profile.first()) {
            assertEquals(expectedProfile.userId, userId)
            assertEquals(expectedProfile.name, name)
            assertEquals(expectedProfile.currentDate, currentDate)
            assertEquals(expectedProfile.currentYear, currentYear)
        }
    }

    @Test
    fun `null id input`() = runTest {
        val profile = getProfileUseCase.invoke(null)
        assertEquals(profile, emptyFlow())
    }

    @Test
    fun `empty id input`() = runTest {
        val profile = getProfileUseCase.invoke("")
        assertEquals(profile, emptyFlow())
    }

    @Test
    fun `date doesn't map`() = runTest {
        coEvery { profileRepository.getProfileData(any()) } returns testProfileDataEdgeCase

        val profile = getProfileUseCase.invoke(ID_1)
        with(profile.first()) {
            assertEquals(expectedProfileEdgeCase.userId, userId)
            assertEquals(expectedProfileEdgeCase.name, name)
            assertEquals(expectedProfileEdgeCase.currentDate, currentDate)
            assertEquals(expectedProfileEdgeCase.currentYear, currentYear)
        }
    }
}