package com.lsn.module.music.repository.net.i

import com.lsn.comm.core.net.ResponseEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:51
 * @Description :
 */
interface IMusicRepository {

    suspend fun getBanner(tag:String): Flow<ResponseEntity>

}