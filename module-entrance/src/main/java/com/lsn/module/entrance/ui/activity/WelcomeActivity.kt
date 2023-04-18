package com.pmisy.roomkb.ui.activity

import android.app.Dialog
import android.content.DialogInterface
import android.view.View
import com.lsn.lib.base.ConventionalListener
import com.pmisy.roomkb.ui.viewmodel.WelcomeViewModel
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.ActivityWelcomeBinding
import com.pmisy.roomkb.manager.SPManager

//import com.umeng.commonsdk.UMConfigure


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
class WelcomeActivity :
    BaseKanbanActivity<WelcomeViewModel, ActivityWelcomeBinding>(R.layout.activity_welcome) {


    var inflate: View? = null
    var dialog: Dialog? = null

    override fun initView() {
        super.initView()

        val auth = SPManager.instance.getAuth()

        if (auth) {

        } else {
            showConventionalConfirmDialog(
                "用户协议及隐私政策概要",
                "请务必理解《服务协议》和《隐私政策》各条款，未经您的授权同意，我们不会主动向任何第三方共享您的个人信息。",
                object : ConventionalListener {
                    override fun onAgree(dialog: DialogInterface) {

                    }

                    override fun onCancel() {
                    }
                })
        }


    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }


/*    fun t(): Boolean {

//        sharedPreferencesHelper = SharedPreferencesHelper(this, "umeng")
        val okClode = SPUtils.getInstance().getInt(CoreConstant.umeng, 0)

        */
    /*** sp中uminit为1已经同意隐私协议*//*
        return if (okClode == 1) {
            */
    /***
     * 此时初始化在appcation中进行，这里不做任何友盟初始化操作，直接跳转activity
     *
     *//*
            //推送全屏消息设置（非初始化操作，这是推送全屏消息设置）
            val mInAppMessageManager: InAppMessageManager =
                InAppMessageManager.getInstance(this@WelcomeActivity)
            mInAppMessageManager.setInAppMsgDebugMode(true)
            //跳转homeactivity
            mInAppMessageManager.setMainActivityPath("com.umeng.soexample.HomeActivity")

            //推送平台多维度推送决策必须调用方法(需要同意隐私协议之后初始化完成调用)
            PushAgent.getInstance(this).onAppStart()
            super.onCustomPretreatment()
        } else {
            */
    /*** 隐私协议授权弹窗 *//*
            dialog()
            true
        }
    }*/


/*    @SuppressLint("ResourceType")
    fun dialog() {
        dialog = Dialog(this, com.lsn.comm.obs.R.style.dialog)
        inflate = LayoutInflater.from(this).inflate(com.lsn.comm.obs.R.layout.diaologlayout, null)
        inflate?.let {
            val succsebtn: TextView = it.findViewById(com.lsn.comm.obs.R.id.succsebtn) as TextView
            val canclebtn: TextView = it.findViewById(com.lsn.comm.obs.R.id.caclebtn) as TextView

            succsebtn.setOnClickListener {
                */
    /*** uminit为1时代表已经同意隐私协议，sp记录当前状态 *//*
//                    sharedPreferencesHelper.put("uminit", "1")
                SPUtils.getInstance().put(CoreConstant.umeng, 1)
                UMConfigure.submitPolicyGrantResult(applicationContext, true)
                */
    /*** 友盟sdk正式初始化 *//*
                val umInitConfig = UmInitConfig()
                umInitConfig.UMinit(applicationContext)
                //QQ官方sdk授权
                Tencent.setIsPermissionGranted(true)
                //推送平台多维度推送决策必须调用方法(需要同意隐私协议之后初始化完成调用)
                PushAgent.getInstance(this@WelcomeActivity).onAppStart()
                //关闭弹窗
                dialog?.dismiss()

                //跳转到HomeActivity
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            canclebtn.setOnClickListener {
                dialog?.dismiss()
                //不同意隐私协议，退出app
                UMConfigure.submitPolicyGrantResult(applicationContext, false)
                Process.killProcess(Process.myPid())
            }
            dialog?.let { dialog ->
                dialog.setContentView(it)
                val dialogWindow: Window? = dialog.window
                dialogWindow?.setGravity(Gravity.CENTER)
                dialog.setCancelable(false)
                dialog.show()
            }
        }
    }*/
}