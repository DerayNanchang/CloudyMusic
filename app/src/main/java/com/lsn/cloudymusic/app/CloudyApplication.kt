package com.lsn.cloudymusic.app

import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.comm.core.app.BaseCoreApplication
import com.lsn.lib.net.BuildConfig
import dagger.hilt.android.HiltAndroidApp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 09:12
 * @Description :
 */
@HiltAndroidApp
class CloudyApplication : BaseCoreApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}