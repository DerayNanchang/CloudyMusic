package com.lsn.comm.core.exts

import android.hardware.Camera
import com.lsn.comm.core.loader.ErrorCallback
import com.lsn.comm.core.loader.LoadingCallback
import com.lsn.lib.base.loader.Loader

/**
 * @Author : lsn
 * @CreateTime : 2023/4/14 上午 08:56
 * @Description :
 */
fun initLoaderDefault() {
    Loader.beginBuilder()
//        .addCallback(LoadingCallback::class.java)
        .addCallback(ErrorCallback::class.java)
        .commit()
}