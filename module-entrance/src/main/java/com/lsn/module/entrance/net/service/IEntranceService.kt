package com.lsn.module.entrance.net.service

import com.lsn.module.provider.comm.api.ApiConstants
import com.lsn.module.entrance.entity.HPImageArchiveEntity
import com.lsn.module.entrance.entity.HitokotoEncodeEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:55
 * @Description :
 */
interface IEntranceService {




    @GET(ApiConstants.Entrance.HP_IMAGE_ARCHIVE)
    suspend fun getHPImageArchive(
        @Query("format") format: String, @Query("idx") idx: Int, @Query("n") n: Int
    ): HPImageArchiveEntity

    @GET(ApiConstants.Entrance.LOGIN_QR_KEY)
    suspend fun getLoginQrKey(): HPImageArchiveEntity

    @GET(ApiConstants.Entrance.LOGIN_QR_CREATE)
    suspend fun getLoginQrCreate(
        @Query("format") format: String, @Query("idx") idx: Int, @Query("n") n: Int
    ): HPImageArchiveEntity

    @GET(ApiConstants.Entrance.LOGIN_QR_CHECK)
    suspend fun getLoginQrCheck(
        @Query("format") format: String, @Query("idx") idx: Int, @Query("n") n: Int
    ): HPImageArchiveEntity

}