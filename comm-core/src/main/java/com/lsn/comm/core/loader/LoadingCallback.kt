package com.lsn.comm.core.loader

import com.lsn.comm.core.R
import com.lsn.lib.base.loader.callback.Callback


/**
 * @Author : lsn
 * @CreateTime : 2023/4/14 上午 09:08
 * @Description :
 */
class LoadingCallback: Callback() {

    override fun layoutId(): Int = R.layout.layout_loading
}