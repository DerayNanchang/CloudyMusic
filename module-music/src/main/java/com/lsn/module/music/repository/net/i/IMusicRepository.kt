package com.lsn.module.music.repository.net.i

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.module.music.entity.MusicAlbumNew
import com.lsn.module.music.entity.MusicArtistsRoot
import com.lsn.module.music.entity.MusicMVRoot
import com.lsn.module.music.net.service.IMusicService
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:51
 * @Description :
 */
interface IMusicRepository {

    suspend fun getBanner(tag: String): Flow<ResponseEntity>

    suspend fun getAlbumNewest(tag: String): Flow<ResponseEntity>

    suspend fun getAlbumNew(tag: String): Flow<ResponseEntity>

    suspend fun getMV(tag: String): Flow<ResponseEntity>

    suspend fun getArtists(tag: String): Flow<ResponseEntity>

}