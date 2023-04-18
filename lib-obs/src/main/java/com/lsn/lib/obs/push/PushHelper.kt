package com.lsn.lib.obs.push

import android.content.Context
import com.umeng.commonsdk.UMConfigure


/**
 * @Author : lsn
 * @CreateTime : 2023/4/14 下午 02:50
 * @Description :
 */
object PushHelper {

    fun init(context: Context) {
//        UMConfigure.init(context, appkey, channel, UMConfigure.DEVICE_TYPE_PHONE, "")
        //注册推送
//        PushAgent.getInstance(context).register(object : UPushRegisterCallback {
//
//            override fun onSuccess(deviceToken: String) {
//                //注册成功后返回deviceToken，deviceToken是推送消息的唯一标志
////                Log.i(TAG, "注册成功 deviceToken:" + deviceToken);
//                println("注册成功 deviceToken:$deviceToken")
//            }
//
//            override fun onFailure(errCode: String, errDesc: String) {
////                Log.e(TAG, "注册失败 " + "code:" + errCode + ", desc:" + errDesc);
//                println("注册失败 code:$errCode, desc:$errDesc")
//            }
//        })
    }
}