package com.lsn.comm.core.callbacks

import android.util.SparseArray
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.util.forEach
import androidx.databinding.library.baseAdapters.BR
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.lib.base.loader.LoaderService
import com.lsn.lib.base.loader.callback.Callback

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:42
 * @Description :
 */
interface ICore {


    /**
     * 界面状态管理
     * @return LoaderView?
     */
    //fun getLoader(): LoaderService? = null

    /**
     * 界面状态注册View
     * 为null将不会注册[LoaderService]
     * @return View?
     */
    //fun loaderRegisterView(): View? = null

    /**
     * 默认的状态
     * @return LoaderStatus
     */
    //fun loaderDefaultCallback(): Class<out Callback>? = null

    /**
     * 错误、空界面重新
     */
    //fun onPageReLoad() {}

    /**
     * 是否为退出界面
     * @return Boolean
     */
    //fun isExitPage(): Boolean = false

    /**
     * 显示退出提示
     */
    //fun showExitTips()

}