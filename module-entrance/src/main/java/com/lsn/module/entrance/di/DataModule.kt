package com.pmisy.roomkb.di

import com.pmisy.roomkb.app.PMIJEKanbanApplication
import com.pmisy.roomkb.entity.login.UserInfEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideUserInfo():UserInfEntity?{
        return PMIJEKanbanApplication.application.getUserInfo()
    }
}