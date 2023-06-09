package com.lsn.module.music.repository.net.i

import androidx.annotation.WorkerThread
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.module.music.entity.*
import com.lsn.module.music.net.service.IMusicService
import com.lsn.module.provider.comm.api.ApiConstants
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:51
 * @Description :
 */
interface IMusicRepository {


    @WorkerThread
    suspend fun getHitokotoEncode(tag: String): Flow<ResponseEntity>
    suspend fun getBanner(tag: String): Flow<ResponseEntity>


    suspend fun getPersonalized(tag: String, limit: Int): Flow<ResponseEntity>


    suspend fun getRelatedPlaylist(tag: String): Flow<ResponseEntity>
    suspend fun getAlbumNewest(tag: String): Flow<ResponseEntity>

    suspend fun getAlbumNew(tag: String): Flow<ResponseEntity>

    suspend fun getMV(tag: String): Flow<ResponseEntity>

    suspend fun getArtists(tag: String): Flow<ResponseEntity>


    suspend fun getUserPlaylist(
        tag: String,
        userId: Long,
        limit: Int,
        offset: Int
    ): Flow<ResponseEntity>


    suspend fun getToplistDetail(tag:String): Flow<ResponseEntity>


    suspend fun getPlaylistDetail(tag: String, id: Long): Flow<ResponseEntity>

}