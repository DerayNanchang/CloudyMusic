package com.lsn.module.entrance.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.os.Process
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.obs.core.OBSConfig
import com.lsn.lib.obs.core.OBSHelp
import com.lsn.module.entrance.R
import com.lsn.module.entrance.databinding.ActivityAuthBinding
import com.lsn.module.provider.comm.manager.SPManager
import com.lsn.module.entrance.ui.viewmodel.AuthViewModel
import com.lsn.module.provider.comm.constant.Constants
import com.umeng.commonsdk.UMConfigure


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
@Route(path = Constants.RouterPath.ENTRANCE.AUTH)
class AuthActivity : BaseCoreActivity<AuthViewModel, ActivityAuthBinding>(R.layout.activity_auth) {


    override fun getViewModelClass(): Class<AuthViewModel> {
        return AuthViewModel::class.java
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
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
//            var path = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F09ee8879-d22b-4beb-bc30-20b0997b3539%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1684992747&t=d606801c6dfd930fe98dca289f13ac35"
            startActivity<WelcomeActivity>()
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
                            startActivity<WelcomeActivity>()
                            finish()
                        }

                        override fun onCancel() {

                            OBSHelp.instance.submitPolicyGrantResult(applicationContext, false)
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