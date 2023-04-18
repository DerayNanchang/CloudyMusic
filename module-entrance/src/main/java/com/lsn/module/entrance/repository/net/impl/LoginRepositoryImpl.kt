package com.pmisy.roomkb.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.pmisy.roomkb.net.client.LoginClient
import com.pmisy.roomkb.repository.net.i.ILoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 05:19
 * @Description :
 */
class LoginRepositoryImpl @Inject constructor(var commClient: LoginClient) : ILoginRepository {
    override suspend fun login(tag: String, data: String): Flow<ResponseEntity> {
        return flowTranData(tag, commClient.login(data))
    }
}