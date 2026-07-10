package com.thedullpencil.data.repository

import com.thedullpencil.data.model.ProfileData

interface ProfileRepository {
    /**
     * Gets data for a specific profile
     */
    suspend fun getProfileData(profileId: String): ProfileData
}