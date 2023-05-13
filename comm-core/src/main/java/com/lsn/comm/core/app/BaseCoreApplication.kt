package com.lsn.comm.core.app

import android.app.Application
import com.lsn.comm.core.CoreExecutor
import com.lsn.comm.core.utils.MMKVUtil
import com.lsn.lib.net.core.NetConfigEntity
import com.lsn.lib.obs.core.OBSConfig
import com.lsn.lib.utils.util.SPUtils
import java.io.File


/**
 * @Author : lsn
 * @CreateTime : 2023/3/9 下午 04:51
 * @Description :
 */

val appContext: BaseCoreApplication by lazy { BaseCoreApplication.app }

abstract class BaseCoreApplication : Application() {

    lateinit var netConfigEntity: NetConfigEntity
    lateinit var apkDir: File
    lateinit var imgDir: File

    companion object {
        lateinit var app: BaseCoreApplication
    }

    override fun onCreate() {
        super.onCreate()
        app = this@BaseCoreApplication
        // 初始化
        CoreExecutor.initUtil(this)
        CoreExecutor.initView(this)
        netConfigEntity = CoreExecutor.initNetWork(this)



        val auth = SPUtils.getInstance().getBoolean("umengAuth")
        OBSConfig.preInitMeng(this)
        if (auth) {
            OBSConfig.initUMeng(this)
        }
        MMKVUtil.init(this)

        //建议子线程中初始化SDK（启动优化）
        //建议子线程中初始化SDK（启动优化）
//        initFile()

        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
//        UMConfigure.init(Context context,String appkey,String channel,int deviceType,String pushSecret);

    }


}