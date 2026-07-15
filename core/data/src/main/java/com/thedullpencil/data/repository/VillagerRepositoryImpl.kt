package com.thedullpencil.data.repository

import com.thedullpencil.core.data.R
import com.thedullpencil.data.model.VillagerData
import com.thedullpencil.data.util.JsonResourceLoader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VillagerRepositoryImpl @Inject constructor(
    private val loader: JsonResourceLoader,
) : VillagerRepository {
    private val allVillagers: List<VillagerData> by lazy {
        loader.decode(R.raw.villager_data)
    }

    override suspend fun getVillagers(): List<VillagerData> = allVillagers

    override suspend fun getVillager(name: String): VillagerData =
        allVillagers.first { it.name == name }
}
