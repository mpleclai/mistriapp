package com.thedullpencil.data.repository

import com.thedullpencil.data.model.VillagerData
import kotlinx.coroutines.flow.Flow

interface VillagerRepository {
    /**
     * Gets the available villagers as a stream
     */
    suspend fun getVillagers(): List<VillagerData>

    /**
     * Gets data for a specific villager
     */
    suspend fun getVillager(name: String): VillagerData
}