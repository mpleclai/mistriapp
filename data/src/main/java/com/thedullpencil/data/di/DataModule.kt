package com.thedullpencil.data.di

import com.thedullpencil.data.repository.VillagerRepositoryImpl
import com.thedullpencil.data.repository.VillagerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsVillagersRepository(
        villagerRepository: VillagerRepositoryImpl,
    ): VillagerRepository
}