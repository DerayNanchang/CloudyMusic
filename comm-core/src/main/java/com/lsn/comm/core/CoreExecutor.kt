package com.lsn.comm.core

import android.app.Application
import androidx.databinding.library.baseAdapters.BR
import com.lsn.lib.base.FileHelp
import com.lsn.lib.net.core.NetConfigEntity
import com.lsn.lib.net.core.setOkCode
import com.lsn.lib.ui.XUI
import com.lsn.lib.ui.widget.rv.PageRefreshLayout
import com.lsn.lib.ui.widget.rv.state.StateConfig
import com.lsn.lib.ui.widget.rv.utils.BRV
import com.lsn.lib.utils.util.DeviceUtils
import com.lsn.lib.utils.util.Utils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import java.io.File


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 下午 03:48
 * @Description :
 */
object CoreExecutor {

    private var fileName = "CloudyMusic"

    fun initUtil(context: Application) {
        // 初始化 Utils
        initUtils(context)
    }

    fun initView(context: Application) {
        // 初始化BRV
        initBRV()
        // 初始化 UI
        initXUI(context)
    }


    fun initNetWork(context: Application): NetConfigEntity {
        return initNet(context)
    }


    private fun initUtils(context: Application) {
        Utils.init(context)
    }

    /**
     *  初始化 BRV
     */
    private fun initBRV() {


        // 禁止错误缺省页启用下拉刷新
        PageRefreshLayout.startIndex = 0
        PageRefreshLayout.refreshEnableWhenError = false
        // 初始化BindingAdapter的默认绑定ID
        BRV.modelId = BR.item
        /**
         *  推荐在Application中进行全局配置缺省页, 当然同样每个页面可以单独指定缺省页.
         *  具体查看 https://github.com/liangjingkanji/StateLayout
         */
        StateConfig.apply {
            emptyLayout = com.lsn.lib.ui.R.layout.sl_layout_empty
            errorLayout = com.lsn.lib.ui.R.layout.sl_layout_error
            loadingLayout = com.lsn.lib.ui.R.layout.sl_layout_loading
            setRetryIds(com.lsn.lib.ui.R.id.msg, com.lsn.lib.ui.R.id.iv)
            onLoading {
                // 此生命周期可以拿到LoadingLayout创建的视图对象, 可以进行动画设置或点击事件.
            }
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context)
        }


        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }
    }

    private fun initXUI(context: Application) {
        XUI.init(context)
    }

    private fun initNet(context: Application): NetConfigEntity {

        val absolutePath = context.cacheDir.absolutePath
        setOkCode(200)
        return NetConfigEntity(
            bridgeName = "",
            apiServiceUrl = BuildConfig.apiServiceUrl,
            appFileUrl = BuildConfig.appFileUrl,
            versionName = DeviceUtils.getSDKVersionName(),
            versionCode = DeviceUtils.getSDKVersionCode(),
            cacheFilePath = absolutePath,
            diskCacheName = "PMoment",
            diskCacheSize = 10f,           // 单位MB
            defCacheDay = 7f,              // 单位天
            connectTime = 5000,            // 单位毫秒
            readTime = 5000,
            writeTime = 5000,
        )
    }

    fun getApkFile(): File {
        return FileHelp.get().initApkDir(fileName)
    }

    fun getImgFile(): File {
        return FileHelp.get().initImgDir(fileName)
    }
}