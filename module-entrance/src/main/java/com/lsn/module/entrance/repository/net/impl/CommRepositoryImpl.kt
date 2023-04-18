package com.pmisy.roomkb.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.pmisy.roomkb.net.client.CommClient
import com.pmisy.roomkb.repository.net.i.ICommRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 11:37
 * @Description :
 */
class CommRepositoryImpl @Inject constructor(var commClient: CommClient) : ICommRepository {

    override suspend fun getVersionInfo(
        tag: String,
        clientNo: String,
        apkType: String
    ): Flow<ResponseEntity> {
        return flowTranData(tag, commClient.getVersionInfo(clientNo, apkType))
    }

    override suspend fun searchRoom(
        tag: String,
        clientNo: String,
        languageNo: String,
        plantNo: String,
        routeType: String
    ): Flow<ResponseEntity> {
        return flowTranData(tag, commClient.searchRoom(clientNo, languageNo, plantNo, routeType))
    }

    override suspend fun searchProdEquipmentDetailList(
        tag: String,
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String
    ): Flow<ResponseEntity> {
        return flowTranData(
            tag,
            commClient.searchProdEquipmentDetailList(clientNo, plantNo, roomNo, queryDate)
        )
    }

    override suspend fun searchProdOrderDetailList(
        tag: String,
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String
    ): Flow<ResponseEntity> {
        return flowTranData(
            tag,
            commClient.searchProdOrderDetailList(clientNo, plantNo, roomNo, queryDate)
        )
    }
}