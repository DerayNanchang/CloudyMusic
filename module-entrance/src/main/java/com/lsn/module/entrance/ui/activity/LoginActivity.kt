package com.pmisy.roomkb.ui.activity

import android.content.Intent
import android.text.TextUtils
import com.lsn.comm.core.BuildConfig
import com.lsn.comm.core.constant.CoreConstant
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.ActivityLoginBinding
import com.lsn.lib.utils.util.DeviceUtils
import com.lsn.lib.utils.util.MToast
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.comm.VersionEntity
import com.pmisy.roomkb.entity.login.LoginRequEntity
import com.pmisy.roomkb.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:58
 * @Description :
 */
@AndroidEntryPoint
class LoginActivity :
    BaseDLKanbanActivity<LoginViewModel, ActivityLoginBinding>(R.layout.activity_login) {

    private var languageNo = "ZH"
    private var loginType = "android"

    private lateinit var loginReq: LoginRequEntity

    override fun initView() {
        super.initView()
//        PushAgent.getInstance(this).onAppStart();
//        val registrationId = PushAgent.getInstance(this).registrationId
//        println("devicesTo"+registrationId)
    }


    override fun initData() {
        super.initData()
        loginReq = LoginRequEntity()

        // 设置初始化数据
        viewModel.getCacheUserData()?.apply {
            binding.etClient.setText(clientNo)
            binding.etUserName.setText(userNo)
            binding.etPassWord.setText(password)
            this@LoginActivity.languageNo = languageNo

            viewModel.getVersionInfo(clientNo, "ROOMKB")
        }


    }

    override fun initEvent() {
        super.initEvent()

        binding.tvLogin.setOnClickListener {
            // 登录
            if (TextUtils.isEmpty(binding.etClient.text)) {
                MToast.show("请输入集团号")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(binding.etUserName.text)) {
                MToast.show("请输入账号")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(binding.etPassWord.text)) {
                MToast.show("请输入密码")
                return@setOnClickListener
            }


            loginReq.apply {
                password = binding.etPassWord.text.toString()
                languageNo = "ZH"
                loginType = this@LoginActivity.loginType
                userNo = binding.etUserName.text.toString()
                clientNo = binding.etClient.text.toString()
                manufaturer = DeviceUtils.getManufacturer()
                modelNumber = DeviceUtils.getModel()
                systemVersion = DeviceUtils.getSDKVersionName()
                resolution = DeviceUtils.getResolution(windowManager)
                deviceId = DeviceUtils.getUniqueDeviceId()
            }
            // 登录
            viewModel.login(loginReq)
        }

    }

    override fun onResponseReceiver() {
        super.onResponseReceiver()

        viewModel.success.observe(this) {
            when (it.api) {

                ApiConstants.Comm.LOGIN -> {
                    // 跳转到主页
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                ApiConstants.Comm.SEARCH_APK_INFO -> {
                    val versionEntity = it.data as VersionEntity
                    versionEntity.apply {
                        var path = BuildConfig.appFileUrl + downloadURL
                        var title = "发现新版本"
                        var desc = buildUpdateDescription
                        var isForce = (needForceUpdate == 1)
                        var versionCode = buildVersion
//                        checkNewVersion(path, title, desc, isForce, versionCode, null)
                    }
                }
            }
        }
    }


    override fun getFilePath() = File(CoreConstant.getApkFile(), BuildConfig.appName + ".apk")
}