package com.lsn.module.entrance.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    @Provides
//    fun provideUserInfo():UserInfEntity?{
//        return PMIJEKanbanApplication.application.getUserInfo()
//    }
}