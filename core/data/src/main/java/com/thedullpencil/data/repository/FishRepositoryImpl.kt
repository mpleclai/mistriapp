package com.thedullpencil.data.repository

import com.thedullpencil.core.data.R
import com.thedullpencil.data.model.FishData
import com.thedullpencil.data.util.JsonResourceLoader
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FishRepositoryImpl @Inject constructor(
    private val loader: JsonResourceLoader,
) : FishRepository {
    private val allFish: List<FishData> by lazy {
        loader.decode(R.raw.fish_data)
    }

    override suspend fun getFishData(name: String): FishData? =
        allFish.firstOrNull { it.name == name }

    override suspend fun getAllFish(): List<FishData> = allFish
}
