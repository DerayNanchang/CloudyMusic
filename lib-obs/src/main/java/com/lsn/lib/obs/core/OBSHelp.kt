package com.lsn.lib.obs.core

import android.content.Context
import com.umeng.commonsdk.UMConfigure
import com.umeng.umcrash.UMCrash


/**
 * @Author : lsn
 * @CreateTime : 2023/4/14 上午 11:23
 * @Description : App监听提供类
 */
class OBSHelp private constructor() {

    companion object {

        val instance = GetInstance.holder
    }

    private object GetInstance {
        val holder = OBSHelp()
    }


    fun pushGenerateCustomLog(e: Throwable) {
        UMCrash.generateCustomLog(e, "test")
    }


    fun submitPolicyGrantResult(context:Context,isSubmit:Boolean){
        UMConfigure.submitPolicyGrantResult(context, isSubmit)
    }

}