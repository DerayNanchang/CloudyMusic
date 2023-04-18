package com.pmisy.roomkb.net.service

import com.lsn.lib.net.core.entity.ResponseApi
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.comm.KBEqRoomEntity
import com.pmisy.roomkb.entity.comm.KBORRoomEntity
import com.pmisy.roomkb.entity.comm.RoomEntity
import com.pmisy.roomkb.entity.comm.VersionEntity
import com.pmisy.roomkb.entity.login.LoginRespEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:53
 * @Description :
 */
interface CommApiService {

    @FormUrlEncoded
    @POST(ApiConstants.Comm.LOGIN)
    suspend fun login(@Field("data") data: String): ResponseApi<LoginRespEntity>


    @GET(ApiConstants.Comm.SEARCH_APK_INFO)
    suspend fun getVersionInfo(
        @Query("clientNo") clientNo: String,
        @Query("apkType") apkType: String,
    ): ResponseApi<VersionEntity>


    @GET(ApiConstants.Comm.SEARCH_ROOM)
    suspend fun searchRoom(
        @Query("clientNo") clientNo: String,
        @Query("languageNo") languageNo: String,
        @Query("plantNo") plantNo: String,
        @Query("routeType") routeType: String,
    ): ResponseApi<List<RoomEntity>>

    //    clientNo=100&plantNo=1000&roomNo=A01&queryDate=2023-03-29 00:00:00
    @GET(ApiConstants.Comm.SEARCH_PROD_EQUIPMENT_DETAIL_LIST)
    suspend fun searchProdEquipmentDetailList(
        @Query("clientNo") clientNo: String,
        @Query("plantNo") plantNo: String,
        @Query("roomNo") roomNo: String,
        @Query("queryDate") queryDate: String,
    ): ResponseApi<KBEqRoomEntity>

    //clientNo=100&plantNo=1000&roomNo=A03&queryDate=2023-03-29 00:00:00
    @GET(ApiConstants.Comm.SEARCH_PROD_ORDER_DETAIL_LIST)
    suspend fun searchProdOrderDetailList(
        @Query("clientNo") clientNo: String,
        @Query("plantNo") plantNo: String,
        @Query("roomNo") roomNo: String,
        @Query("queryDate") queryDate: String,
    ): ResponseApi<KBORRoomEntity>
}