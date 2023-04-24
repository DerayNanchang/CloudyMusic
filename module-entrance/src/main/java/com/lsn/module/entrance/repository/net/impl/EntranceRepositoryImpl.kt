package com.lsn.module.entrance.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.net.core.annotation.NetBaseUrlFunc
import com.lsn.lib.net.core.annotation.NetResponseFunc
import com.lsn.module.entrance.net.client.EntranceClient
import com.lsn.module.entrance.repository.net.i.IEntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class EntranceRepositoryImpl @Inject constructor(var entranceClient: EntranceClient) : IEntranceRepository {

    override suspend fun getHitokotoEncode(tag: String): Flow<ResponseEntity> {
        return flowTranData(tag, entranceClient.getHitokotoEncode())
    }
}