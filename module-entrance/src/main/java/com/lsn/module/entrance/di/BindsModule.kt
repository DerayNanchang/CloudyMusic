package com.lsn.module.entrance.di

import com.pmisy.roomkb.repository.net.i.ICommRepository
import com.pmisy.roomkb.repository.net.i.ILoginRepository
import com.pmisy.roomkb.repository.net.impl.CommRepositoryImpl
import com.pmisy.roomkb.repository.net.impl.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): ILoginRepository


    @Binds
    fun bindCommRepository(commRepository: CommRepositoryImpl): ICommRepository

}