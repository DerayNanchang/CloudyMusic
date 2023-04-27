package com.lsn.module.music.net.service

import com.lsn.module.music.entity.MusicBannerItem
import com.lsn.module.music.entity.MusicBannerList
import com.lsn.module.provider.comm.api.ApiConstants
import retrofit2.http.GET


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:55
 * @Description :
 */
interface IMusicService {


    @GET(ApiConstants.Music.BANNER)
    suspend fun getBanner(): MusicBannerList



}