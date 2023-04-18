package com.lsn.comm.core.exts

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import com.google.gson.JsonSyntaxException
import com.lsn.comm.core.app.appContext
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.lib.base.ui.IBUI
import com.lsn.lib.net.core.exceptions.HttpStatusCodeException
import com.lsn.lib.net.core.exceptions.ParseDataException
import kotlinx.coroutines.TimeoutCancellationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @Author : lsn
 * @CreateTime : 2023/3/22 上午 10:39
 * @Description :
 */

const val NET_ERROR = "当前无网络，请检查你的网络设置"
const val NET_CONNECT_ERROR = "网络不给力，请稍候重试！"
const val NET_TIME_OUT = "连接超时,请稍后再试"
const val NET_CODE_ERROR = "Http状态码异常"
const val NET_PARSE_ERROR = "数据解析失败,请检查数据是否正确"
const val NET_UNKNOWN = "请求失败，请稍后再试"

val Throwable.msg: String
    get() {
        val customResult = customParseException?.invoke(this)
        if(!customResult.isNullOrEmpty()){
            return customResult
        }
        return if (this is UnknownHostException) {
            //网络异常
            NET_ERROR
        } else if (
            this is SocketTimeoutException  //okHttp全局设置超时
            || this is TimeoutException     //方法超时
            || this is TimeoutCancellationException  //协程超时
        ) {
            NET_TIME_OUT
        } else if (this is ConnectException) {
            NET_CONNECT_ERROR
        } else if (this is HttpStatusCodeException) {
            NET_CODE_ERROR
        } else if (this is JsonSyntaxException) {
            //请求成功，但Json语法异常,导致解析失败
            NET_PARSE_ERROR
        } else if (this is ParseDataException) {
            // ParseException异常表明请求成功，但是数据不正确 msg为空，显示code
            this.message ?: errorCode
        } else {
            NET_UNKNOWN
        }
    }


internal var customParseException: ((Throwable) -> String?)? = null


fun isNetworkConnected(): Boolean {
    val manager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = manager.activeNetworkInfo
    networkInfo?.let {
        return it.isAvailable
    }
    return false
}
