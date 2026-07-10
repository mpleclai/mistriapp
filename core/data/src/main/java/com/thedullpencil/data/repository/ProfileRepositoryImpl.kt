package com.thedullpencil.data.repository

import com.thedullpencil.data.model.ProfileData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    //TODO -  temporary sample data implementation; replace with actual data
    override suspend fun getProfileData(profileId: String): ProfileData = ProfileData(
        userId = "1",
        name = "Joey",
        day = 10,
        season = "Spring",
        year = 1
    )
}