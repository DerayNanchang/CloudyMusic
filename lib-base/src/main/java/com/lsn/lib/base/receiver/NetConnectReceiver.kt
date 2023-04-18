package com.lsn.lib.base.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lsn.lib.base.BaseConstant
import com.lsn.lib.base.bus.LiveBus
import com.lsn.lib.utils.util.MToast
import com.lsn.lib.utils.util.NetworkUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 10:09
 * @Description :
 */
class NetConnectReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        when (NetworkUtils.getNetworkType()) {
            NetworkUtils.NetworkType.NETWORK_2G -> {
                MToast.show("2G网络");
                setNetStatus(NetworkUtils.NetworkType.NETWORK_2G)
            }
            NetworkUtils.NetworkType.NETWORK_3G -> {
                MToast.show("3G网络");
                setNetStatus(NetworkUtils.NetworkType.NETWORK_3G)
            }

            NetworkUtils.NetworkType.NETWORK_4G -> {
                MToast.show("4G网络")
                setNetStatus(NetworkUtils.NetworkType.NETWORK_4G)
            }
            NetworkUtils.NetworkType.NETWORK_5G -> {
                MToast.show("5G网络")
                setNetStatus(NetworkUtils.NetworkType.NETWORK_5G)
            }
            NetworkUtils.NetworkType.NETWORK_WIFI -> {
                MToast.show("WIFI网络");
                setNetStatus(NetworkUtils.NetworkType.NETWORK_WIFI)
            }
            NetworkUtils.NetworkType.NETWORK_NO -> {
                MToast.show("网络异常,请查看网络环境")
                setNetStatus(NetworkUtils.NetworkType.NETWORK_NO)
            }
            else -> {
                MToast.show("未知网络")
            }
        }
    }


    private fun setNetStatus(status: NetworkUtils.NetworkType) {
        LiveBus.get().with(BaseConstant.Bus.NET_CONNECT_STATUS)
            .postValue(status)
    }
}