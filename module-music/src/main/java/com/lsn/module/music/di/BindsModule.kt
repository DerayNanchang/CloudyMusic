package com.lsn.module.music.di

import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.music.repository.net.impl.MusicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {


    @Binds
    fun bindMusicRepository(musicRepositoryImpl: MusicRepositoryImpl): IMusicRepository

}