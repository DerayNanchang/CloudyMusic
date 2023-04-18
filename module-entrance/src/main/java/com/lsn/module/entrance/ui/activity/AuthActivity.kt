package com.pmisy.roomkb.ui.activity

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Process
import android.view.View
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.obs.core.OBSConfig
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.ActivityAuthBinding
import com.pmisy.roomkb.manager.SPManager
import com.pmisy.roomkb.ui.viewmodel.AuthViewModel
import com.umeng.commonsdk.UMConfigure

//import com.umeng.commonsdk.UMConfigure


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
class AuthActivity :
    BaseKanbanActivity<AuthViewModel, ActivityAuthBinding>(R.layout.activity_auth) {


    override fun initView() {
        super.initView()

        val auth = SPManager.instance.getAuth()

        if (auth) {

            /***
             *  此时初始化在appcation中进行，这里不做任何友盟初始化操作，直接跳转activity
             *
             */
            //推送全屏消息设置（非初始化操作，这是推送全屏消息设置）
//            val mInAppMessageManager = InAppMessageManager.getInstance(this)
//            mInAppMessageManager.setInAppMsgDebugMode(true)
            //跳转homeactivity
//            mInAppMessageManager.setMainActivityPath("com.umeng.soexample.HomeActivity")
            //推送平台多维度推送决策必须调用方法(需要同意隐私协议之后初始化完成调用)
//            PushAgent.getInstance(this).onAppStart()

            val intent = Intent(this@AuthActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            runOnUiThread {
                showConventionalConfirmDialog(
                    "用户协议及隐私政策概要",
                    "请务必理解《服务协议》和《隐私政策》各条款，未经您的授权同意，我们不会主动向任何第三方共享您的个人信息。",
                    object : ConventionalListener {
                        override fun onAgree(dialog: DialogInterface) {

                            /*** uminit为1时代表已经同意隐私协议，sp记录当前状态 */
                            SPManager.instance.putAuth(true)
                            UMConfigure.submitPolicyGrantResult(applicationContext, true)
                            /*** 友盟sdk正式初始化 */
                            OBSConfig.initUMeng(applicationContext)
                            //QQ官方sdk授权
//                        Tencent.setIsPermissionGranted(true)
                            //关闭弹窗
                            dialog.dismiss()
                            //推送平台多维度推送决策必须调用方法(需要同意隐私协议之后初始化完成调用)
//                            PushAgent.getInstance(this@AuthActivity).onAppStart()
                            //跳转到HomeActivity
                            val intent = Intent(this@AuthActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                        override fun onCancel() {

                            UMConfigure.submitPolicyGrantResult(applicationContext, false)
                            //不同意隐私协议，退出app
                            Process.killProcess(Process.myPid())
                        }
                    })
            }
        }


    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }
}