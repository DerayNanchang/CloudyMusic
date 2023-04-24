package com.lsn.module.entrance.di

import com.lsn.module.entrance.repository.net.i.IEntranceRepository
import com.lsn.module.entrance.repository.net.impl.EntranceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindEntranceRepository(entranceRepositoryImpl: EntranceRepositoryImpl): IEntranceRepository

}