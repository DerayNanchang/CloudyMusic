package com.lsn.lib.base.ui.activity

import android.os.Bundle
import com.lsn.lib.base.exts.addNetListenEnabled
import com.lsn.lib.base.receiver.NetConnectReceiver


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 10:03
 * @Description :
 */
class BaseBroadcastActivity : BaseActivity() {

    private var receiver: NetConnectReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 添加网络监听
        receiver = addNetListenEnabled()

    }



    override fun onDestroy() {
        super.onDestroy()

        receiver?.apply {
            this@BaseBroadcastActivity.unregisterReceiver(this)
            receiver = null
        }

    }

}