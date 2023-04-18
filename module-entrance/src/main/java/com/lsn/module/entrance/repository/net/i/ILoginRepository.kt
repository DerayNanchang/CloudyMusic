package com.pmisy.roomkb.repository.net.i

import androidx.annotation.WorkerThread
import com.lsn.comm.core.net.ResponseEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 05:16
 * @Description :
 */
interface ILoginRepository {

    @WorkerThread
    suspend fun login(tag: String, data: String): Flow<ResponseEntity>

}