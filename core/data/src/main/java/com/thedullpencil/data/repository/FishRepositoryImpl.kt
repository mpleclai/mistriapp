package com.thedullpencil.data.repository

import com.thedullpencil.data.data.fishData
import com.thedullpencil.data.model.FishData
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FishRepositoryImpl @Inject constructor() : FishRepository {
    private val json = Json { ignoreUnknownKeys = true }
    private val allFish: List<FishData> by lazy { json.decodeFromString<List<FishData>>(fishData) }

    override suspend fun getFishData(name: String): FishData? =
        allFish.firstOrNull { it.name == name }

    override suspend fun getAllFish(): List<FishData> = allFish
}
