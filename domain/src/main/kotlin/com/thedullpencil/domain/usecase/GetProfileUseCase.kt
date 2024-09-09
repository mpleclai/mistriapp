package com.thedullpencil.domain.usecase

import com.thedullpencil.core.util.MistriappDate
import com.thedullpencil.core.util.Season.Spring
import com.thedullpencil.core.util.getMistriappDate
import com.thedullpencil.data.repository.ProfileRepository
import com.thedullpencil.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    /**
     * Returns profile data for a specific profile id
     * @param id - unique identifier for a profile
     */
    operator fun invoke(id: String?): Flow<Profile> = if (!id.isNullOrEmpty()) {
        flow {
            emit(

                with(profileRepository.getProfileData(id)) {
                    Profile(
                        userId = userId,
                        name = name,
                        currentYear = year,
                        currentDate = getMistriappDate(season, day) ?: MistriappDate(Spring, 1)
                    )
                }
            )
        }
    } else emptyFlow()
}