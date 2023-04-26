package com.lsn.module.provider.comm.manager

import com.lsn.comm.core.utils.MMKVUtil
import com.lsn.lib.utils.util.SPUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 08:17
 * @Description :
 */
class SPManager private constructor() {

    companion object {
        const val TOKEN_NO = "TOKEN_NO"
        const val LOGIN_INFO = "LoginInfo"
        const val UMENG_AUTH = "umengAuth"
        const val USERNAME = "USERNAME"
        const val KANBAN_MAX_COUNT = "kanban_max_count"
        const val KANBAN_MAX_COUNT_OR = "kanban_max_count_or"

        val instance = GetInstance.holder
    }

    private object GetInstance {
        val holder = SPManager()
    }


    fun putToken(value: String) {
        SPUtils.getInstance().put(TOKEN_NO, value)
    }


    fun getToken(): String {
        return SPUtils.getInstance().getString(TOKEN_NO)
    }

    fun putAuth(value: Boolean) {
        SPUtils.getInstance().put(UMENG_AUTH, value)
    }

    fun getAuth(): Boolean {
        return SPUtils.getInstance().getBoolean(UMENG_AUTH)
    }






    private fun putString(key: String, value: String) {
        SPUtils.getInstance().put(getUserNo() + "_" + key, value)
    }

    private fun getString(key: String): String {
        return SPUtils.getInstance().getString(getUserNo() + "_" + key)
    }


    private fun putInt(key: String, value: Int) {
        SPUtils.getInstance().put(getUserNo() + "_" + key, value)
    }

    private fun getInt(key: String, def: Int): Int {
        return SPUtils.getInstance().getInt(getUserNo() + "_" + key, def)
    }

    private fun putBoolean(key: String, value: Boolean) {
        SPUtils.getInstance().put(getUserNo() + "_" + key, value)
    }

    private fun getBoolean(key: String): Boolean {
        return SPUtils.getInstance().getBoolean(getUserNo() + "_" + key, false)
    }


    private fun putKVString(key: String, value: String) {
        MMKVUtil.putString(getUserNo() + "_" + key, value)
    }

    private fun getKVString(key: String): String? {
        return MMKVUtil.getString(getUserNo() + "_" + key)
    }


    private fun putKVInt(key: String, value: Int) {
        MMKVUtil.putInt(getUserNo() + "_" + key, value)
    }

    private fun getKVInt(key: String, def: Int): Int {
        return MMKVUtil.getInt(getUserNo() + "_" + key, def)
    }

    private fun putKVBoolean(key: String, value: Boolean) {
        MMKVUtil.putBoolean(getUserNo() + "_" + key, value)
    }

    private fun getKVBoolean(key: String): Boolean {
        return MMKVUtil.getBoolean(getUserNo() + "_" + key, false)
    }


    private fun putKVLong(key: String, value: Long) {
        MMKVUtil.putLong(getUserNo() + "_" + key, value)
    }

    private fun getKVLong(key: String): Long {
        return MMKVUtil.getLong(getUserNo() + "_" + key, 0L)
    }


    private fun putKVFloat(key: String, value: Float) {
        MMKVUtil.putFloat(getUserNo() + "_" + key, value)
    }

    private fun getKVFloat(key: String): Float {
        return MMKVUtil.getFloat(getUserNo() + "_" + key, 0F)
    }

    private fun putKVDouble(key: String, value: Double) {
        MMKVUtil.putDouble(getUserNo() + "_" + key, value)
    }

    private fun getKVDouble(key: String): Double {
        return MMKVUtil.getDouble(getUserNo() + "_" + key, 0.0)
    }

    private fun getUserNo(): String? {
        return MMKVUtil.getString(USERNAME)
    }


}