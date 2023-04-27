package com.lsn.comm.core.net

import com.lsn.lib.net.core.entity.PageEntity
import com.lsn.lib.net.core.entity.ResponseApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow

/**
 * @Author : lsn
 * @CreateTime : 2023/3/29 下午 03:18
 * @Description :
 */


fun flowTranData(tag: String, responseApi: ResponseApi<*>): Flow<ResponseEntity> {
    val data = responseApi.data
    val entity = when (data) {
        is PageEntity<*> -> {
            val pageEntity = data as PageEntity<*>
            if (data.datas == null) {
                var mData: ArrayList<String> = ArrayList()
                ResponseEntity(tag, mData, pageEntity.curPage, pageEntity.size,pageEntity.total)
            } else {
                ResponseEntity(tag, data.datas, pageEntity.curPage, pageEntity.size,pageEntity.total)
            }
        }
        else -> {
            if (data != null) {
                ResponseEntity(tag, data)
            } else {
                ResponseEntity(tag, "", HttpClient.NULL_DATA, HttpClient.NULL_DATA)
            }
        }
    }
    return flow { emit(entity) }.filter {
        // 拦截异常数据
        it.page != HttpClient.NULL_DATA && it.size != HttpClient.NULL_DATA
    }
}



fun flowTranData(tag: String,data:Any): Flow<ResponseEntity> {
    val data = data
    val entity = when (data) {
        is PageEntity<*> -> {
            val pageEntity = data as PageEntity<*>
            if (data.datas == null) {
                var mData: ArrayList<String> = ArrayList()
                ResponseEntity(tag, mData, pageEntity.curPage, pageEntity.size,pageEntity.total)
            } else {
                ResponseEntity(tag, data.datas, pageEntity.curPage, pageEntity.size,pageEntity.total)
            }
        }
        else -> {
            if (data != null) {
                ResponseEntity(tag, data)
            } else {
                ResponseEntity(tag, "", HttpClient.NULL_DATA, HttpClient.NULL_DATA)
            }
        }
    }
    return flow { emit(entity) }.filter {
        // 拦截异常数据
        it.page != HttpClient.NULL_DATA && it.size != HttpClient.NULL_DATA
    }
}