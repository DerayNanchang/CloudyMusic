package com.lsn.lib.base


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 10:56
 * @Description :
 */
interface BaseConstant {

    /**
     *  事件线的Key
     */
    interface Bus {
        companion object {
            const val NET_CONNECT_STATUS = "NET_CONNECT_STATUS"
        }

    }


}