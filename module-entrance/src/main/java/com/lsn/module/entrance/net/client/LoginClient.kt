package com.pmisy.roomkb.net.client

import com.lsn.comm.core.anotation.ProvideOnlyNetwork
import com.pmisy.roomkb.net.service.CommApiService
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 05:26
 * @Description :
 */
class LoginClient @Inject constructor(
    @ProvideOnlyNetwork private var retrofit: Retrofit,
) {
    suspend fun login(data: String) = retrofit.create(CommApiService::class.java).login(data)

}