package com.lsn.lib.obs.core

import android.content.Context
import com.lsn.lib.obs.BuildConfig
import com.lsn.lib.obs.push.PushHelper
import com.umeng.commonsdk.UMConfigure


/**
 * @Author : lsn
 * @CreateTime : 2023/4/14 上午 11:20
 * @Description :
 */
object OBSConfig {


    fun initUMeng(context: Context) {


//        if (!BuildConfig.DEBUG) {
        Thread {
            UMConfigure.init(
                context,
                BuildConfig.umId,
                "official",
                UMConfigure.DEVICE_TYPE_PHONE,
                BuildConfig.messageSecret
            )
//            PushHelper.init(context)
        }.start()
//        }
    }

    fun preInitMeng(context: Context) {
        Thread {
            //设置LOG开关，默认为false
            UMConfigure.setLogEnabled(true)
            //友盟预初始化
            UMConfigure.preInit(context, BuildConfig.umId, "official")
        }.start()
    }
}