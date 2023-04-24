package com.lsn.comm.core.utils

import android.app.Application
import com.lsn.comm.core.app.appContext
import com.tencent.mmkv.MMKV


/**
 * @Author : lsn
 * @CreateTime : 2023/4/13 下午 05:16
 * @Description :
 */
object MMKVUtil {

    private var USERNAME = "USERNAME"


    private var mMMKV: MMKV? = null

    fun init(application: Application, id: String? = "mmkv") {
        MMKV.initialize(application.filesDir.absolutePath + "/$id")
        mMMKV = MMKV.mmkvWithID(id)
    }

    fun getMMKV(): MMKV {
        if (null == mMMKV) {
            init(appContext)
        }
        return mMMKV!!
    }

    fun putBoolean(key: String, value: Boolean) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getBoolean(key: String, default: Boolean): Boolean =
        getMMKV().decodeBool(getUserNo() + "_" + key, default)

    fun getBoolean(key: String): Boolean =
        getBoolean(getUserNo() + "_" + key, false)

    fun putString(key: String, value: String?) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getString(key: String, default: String?): String? =
        getMMKV().decodeString(getUserNo() + "_" + key, default)

    fun getString(key: String): String? =
        getString(getUserNo() + "_" + key, null)

    fun putInt(key: String, value: Int) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getInt(key: String, default: Int): Int =
        getMMKV().decodeInt(getUserNo() + "_" + key, default)

    fun putLong(key: String, value: Long) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getLong(key: String, default: Long): Long =
        getMMKV().decodeLong(getUserNo() + "_" + key, default)

    fun putDouble(key: String, value: Double) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getDouble(key: String, default: Double): Double =
        getMMKV().decodeDouble(getUserNo() + "_" + key, default)

    fun putFloat(key: String, value: Float) {
        getMMKV().encode(getUserNo() + "_" + key, value)
    }

    fun getFloat(key: String, default: Float): Float =
        getMMKV().decodeFloat(getUserNo() + "_" + key, default)


    private fun getUserNo(): String? {
      return  getMMKV().decodeString(USERNAME, USERNAME)
    }

    fun clear() {
        getMMKV().clear()
    }

    fun clearAll() {
        getMMKV().clearAll()
    }

    fun clearMemoryCache() {
        getMMKV().clearMemoryCache()
    }

}