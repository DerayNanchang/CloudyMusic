package com.lsn.module.entrance.net.service

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.module.entrance.entity.HitokotoEncodeEntity
import retrofit2.http.GET

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:55
 * @Description :
 */
interface IEntranceService {


    @GET("?encode=json")
    suspend fun getHitokotoEncode():HitokotoEncodeEntity

}