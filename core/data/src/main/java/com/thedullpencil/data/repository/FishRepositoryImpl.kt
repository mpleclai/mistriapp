package com.thedullpencil.data.repository

import com.thedullpencil.data.data.fishData
import com.thedullpencil.data.model.FishData
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FishRepositoryImpl @Inject constructor() : FishRepository {
    override suspend fun getFishData(name: String): FishData {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFish(): List<FishData> {
        val withUnknownKeys = Json { ignoreUnknownKeys = true }
        val data = withUnknownKeys.decodeFromString<List<FishData>>(fishData)
        return data
    }
}