package com.thedullpencil.data.repository

import com.thedullpencil.data.model.FishData

interface FishRepository {
    suspend fun getFishData(name: String): FishData

    suspend fun getAllFish(): List<FishData>
}