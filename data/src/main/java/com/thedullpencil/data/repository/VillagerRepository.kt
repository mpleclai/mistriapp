package com.thedullpencil.data.repository

import com.thedullpencil.data.model.Villager
import kotlinx.coroutines.flow.Flow

interface VillagerRepository {
    /**
     * Gets the available villagers as a stream
     */
    fun getVillagers(): Flow<List<Villager>>

    /**
     * Gets data for a specific villager
     */
    fun getVillager(name: String): Flow<Villager>
}