package com.lsn.module.music.net.client

import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.comm.core.net.HttpClient
import com.lsn.lib.net.core.ResponseModel
import com.lsn.lib.net.core.cache.CacheMode
import com.lsn.module.music.entity.MusicBannerItem
import com.lsn.module.music.entity.MusicBannerList
import com.lsn.module.music.net.service.IMusicService
import com.lsn.module.provider.comm.api.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:57
 * @Description :
 */
class MusicClient @Inject constructor(
    @ProvideReadCacheFailedRequestNetwork30 var requestNetwork30: Retrofit,
    var retrofit: HttpClient,
) {


    suspend fun getBanner(): MusicBannerList {
        val linkUrl = retrofit.getLinkUrl()
        retrofit.setCacheModel(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE)
        retrofit.setCacheTime(HttpClient.DAYS_30)
        val build = retrofit.getBuildRetrofit()
            .client(retrofit.getOkHttpClient(ResponseModel.NETEASECLOUD))
            .baseUrl(linkUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return build.create(IMusicService::class.java)
            .getBanner()
    }


}