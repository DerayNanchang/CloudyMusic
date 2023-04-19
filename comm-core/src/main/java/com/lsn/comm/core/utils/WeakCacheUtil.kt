package com.lsn.comm.core.utils

import com.lsn.lib.utils.util.SPUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/4/19 下午 05:26
 * @Description :
 */
object WeakCacheUtil {

    private const val LAUNCHER: String = "launcher_mode"

    /**
     * 是否开启启动页文字
     */
    fun isOpenLauncherText(): Boolean = MMKVUtil.getBoolean(LAUNCHER, true)

}