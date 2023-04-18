package com.pmisy.roomkb.repository.net.i

import androidx.annotation.WorkerThread
import com.lsn.comm.core.net.ResponseEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 10:42
 * @Description :
 */
interface ICommRepository {

    @WorkerThread
    suspend fun getVersionInfo(
        tag: String,
        clientNo: String,
        apkType: String,
    ): Flow<ResponseEntity>


    @WorkerThread
    suspend fun searchRoom(
        tag: String,
        clientNo: String,
        languageNo: String,
        plantNo: String,
        routeType: String
    ): Flow<ResponseEntity>

    @WorkerThread
    suspend fun searchProdEquipmentDetailList(
        tag: String,
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String,
    ): Flow<ResponseEntity>


    @WorkerThread
    suspend fun searchProdOrderDetailList(
        tag: String,
        clientNo: String,
        plantNo: String,
        roomNo: String,
        queryDate: String
    ): Flow<ResponseEntity>
}