package com.thedullpencil.data.di

import com.thedullpencil.data.repository.DefaultVillagerRepository
import com.thedullpencil.data.repository.VillagerRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsVillagersRepository(
        villagerRepository: DefaultVillagerRepository,
    ): VillagerRepository
}