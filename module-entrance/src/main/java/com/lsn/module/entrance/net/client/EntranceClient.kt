package com.lsn.module.entrance.net.client

import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.comm.core.net.HttpClient
import com.lsn.lib.net.core.ResponseModel
import com.lsn.lib.net.core.cache.CacheMode
import com.lsn.module.provider.comm.api.ApiConstants
import com.lsn.module.entrance.entity.HPImageArchiveEntity
import com.lsn.module.entrance.entity.HitokotoEncodeEntity
import com.lsn.module.entrance.net.service.IEntranceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:57
 * @Description :
 */
class EntranceClient @Inject constructor(
    @ProvideReadCacheFailedRequestNetwork30 var requestNetwork30: Retrofit,
    var retrofit: HttpClient,
) {


    suspend fun getHPImageArchive(format: String, idx: Int, n: Int): HPImageArchiveEntity {
        val linkUrl = retrofit.getLinkUrl(ApiConstants.OrderBaseApis.BING)
        retrofit.setCacheModel(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE)
        retrofit.setCacheTime(HttpClient.DAYS_30)
        val build = retrofit.getBuildRetrofit()
            .client(retrofit.getOkHttpClient(ResponseModel.OTHER))
            .baseUrl(linkUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return build.create(IEntranceService::class.java)
            .getHPImageArchive(format,idx, n)
    }

}