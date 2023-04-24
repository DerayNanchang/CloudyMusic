package com.lsn.module.entrance.net.client

import com.lsn.comm.core.anotation.ProvideOnlyNetwork
import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.module.entrance.entity.HitokotoEncodeEntity
import com.lsn.module.entrance.net.service.IEntranceService
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:57
 * @Description :
 */
class EntranceClient @Inject constructor(
    @ProvideReadCacheFailedRequestNetwork30 var requestNetwork30: Retrofit,
    @ProvideOnlyNetwork var retrofit: Retrofit,
) {


    suspend fun getHitokotoEncode(): HitokotoEncodeEntity {
        return requestNetwork30.create(IEntranceService::class.java)
            .getHitokotoEncode()
    }


}