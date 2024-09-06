package com.thedullpencil.data.repository

import com.thedullpencil.data.model.VillagerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class VillagerRepositoryImpl @Inject constructor() : VillagerRepository {

    //TODO -  temporary sample data implementation; replace with actual data input
    override suspend fun getVillagers(): List<VillagerData> = listOf(
        VillagerData("Hayden"),
        VillagerData("Adeline"),
        VillagerData("Baylor"),
        VillagerData("Calderus"),
        VillagerData("Celine"),
        VillagerData("Darcy"),
        VillagerData("March"),
        VillagerData("Errol"),
        VillagerData("Hemlock"),
        VillagerData("Josephine"),
        VillagerData("Reina"),
    )

    override suspend fun getVillager(name: String): VillagerData {
        TODO("Not yet implemented")
    }
}