package com.thedullpencil.data.repository

import com.thedullpencil.data.model.Villager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class DefaultVillagerRepository @Inject constructor() : VillagerRepository {

    //TODO -  temporary sample data implementation; replace with actual data input
    override fun getVillagers(): Flow<List<Villager>> = flow {
        listOf(
            Villager("Hayden"),
            Villager("Adeline"),
            Villager("Baylor"),
            Villager("Calderus"),
            Villager("Celine"),
            Villager("Darcy"),
            Villager("March"),
            Villager("Errol"),
            Villager("Hemlock"),
            Villager("Josephine"),
            Villager("Reina"),
        )
    }

    override fun getVillager(name: String): Flow<Villager> {
        TODO("Not yet implemented")
    }
}