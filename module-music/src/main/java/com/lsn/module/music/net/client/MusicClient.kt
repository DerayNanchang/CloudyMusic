package com.lsn.module.music.net.client

import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.comm.core.net.HttpClient
import retrofit2.Retrofit
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


}