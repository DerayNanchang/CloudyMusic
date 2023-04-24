package com.lsn.module.entrance.repository.net.i

import androidx.annotation.WorkerThread
import com.lsn.comm.core.net.ResponseEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:51
 * @Description :
 */
interface IEntranceRepository {


    @WorkerThread
    suspend fun getHitokotoEncode(tag:String): Flow<ResponseEntity>

}