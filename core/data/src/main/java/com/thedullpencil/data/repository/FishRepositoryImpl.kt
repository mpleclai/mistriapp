package com.thedullpencil.data.repository

import com.thedullpencil.data.data.fishData
import com.thedullpencil.data.model.FishData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FishRepositoryImpl @Inject constructor() : FishRepository {
    private val parsedFishData: List<FishData> by lazy {
        Json { ignoreUnknownKeys = true }.decodeFromString<List<FishData>>(fishData)
    }

    override suspend fun getFishData(name: String): FishData {
        return parsedFishData.firstOrNull { it.name == name }
            ?: error("No fish found for name: $name")
    }

    override suspend fun getAllFish(): List<FishData> {
        return parsedFishData
    }
}