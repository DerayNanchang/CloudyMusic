package com.pmisy.roomkb.net.client

import com.lsn.comm.core.anotation.ProvideOnlyNetwork
import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.lib.net.core.entity.ResponseApi
import com.lsn.module.entrance.entity.KBEqRoomEntity
import com.lsn.module.entrance.entity.KBORRoomEntity
import com.lsn.module.entrance.entity.RoomEntity
import com.lsn.module.entrance.entity.VersionEntity
import com.pmisy.roomkb.net.service.CommApiService
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 11:40
 * @Description :
 */
class CommClient @Inject constructor(
    @ProvideReadCacheFailedRequestNetwork30 var retrofit30: Retrofit,
    @ProvideOnlyNetwork var retrofit: Retrofit
) {

/*    suspend fun getVersionInfo(
        clientNo: String,
        apkType: String,
    ): ResponseApi<VersionEntity> {
        return retrofit.create(CommApiService::class.java)
            .getVersionInfo(clientNo, apkType)
    }


    suspend fun searchRoom(
        clientNo: String,
        languageNo: String,
        plantNo: String,
        routeType: String
    ): ResponseApi<List<RoomEntity>> {
        return retrofit30.create(CommApiService::class.java)
            .searchRoom(clientNo, languageNo, plantNo, routeType)
    }


    suspend fun searchProdEquipmentDetailList(
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String
    ): ResponseApi<KBEqRoomEntity> {
        return retrofit30.create(CommApiService::class.java)
            .searchProdEquipmentDetailList(clientNo, plantNo, roomNo, queryDate)
    }

    suspend fun searchProdOrderDetailList(
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String
    ): ResponseApi<KBORRoomEntity> {
        return retrofit30.create(CommApiService::class.java)
            .searchProdOrderDetailList(clientNo, plantNo, roomNo, queryDate)
    }*/
}