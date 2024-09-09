package com.thedullpencil.data.di

import com.thedullpencil.data.repository.ProfileRepository
import com.thedullpencil.data.repository.ProfileRepositoryImpl
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

    @Binds
    internal abstract fun bindsProfileRepository(
        profileRepository: ProfileRepositoryImpl,
    ): ProfileRepository
}