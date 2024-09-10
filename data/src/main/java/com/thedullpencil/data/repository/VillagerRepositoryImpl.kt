package com.thedullpencil.data.repository

import com.thedullpencil.data.model.VillagerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VillagerRepositoryImpl @Inject constructor() : VillagerRepository {

    //TODO -  temporary sample data implementation; replace with actual data input
    override suspend fun getVillagers(): List<VillagerData> = listOf(
        VillagerData("Hayden", "Spring", 1),
        VillagerData("Adeline", "Spring", 1),
        VillagerData("Baylor", "Spring", 1),
        VillagerData("Calderus", "Spring", 1),
        VillagerData("Celine", "Spring", 1),
        VillagerData("Darcy", "Spring", 1),
        VillagerData("March", "Spring", 1),
        VillagerData("Errol", "Spring", 1),
        VillagerData("Hemlock", "Spring", 1),
        VillagerData("Josephine", "Spring", 1),
        VillagerData("Reina", "Spring", 1),
    )

    //TODO -  temporary sample data implementation; replace with actual data input
    override suspend fun getVillager(name: String): VillagerData =
        VillagerData("March", "Spring", 1)
}