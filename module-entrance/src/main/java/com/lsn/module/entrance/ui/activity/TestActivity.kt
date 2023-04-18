package com.pmisy.roomkb.ui.activity

import com.lsn.comm.core.BuildConfig
import com.lsn.comm.core.constant.CoreConstant
import com.lsn.comm.version.ui.activity.BaseVersionActivity
import com.lsn.lib.base.PermissionCategory
import com.lsn.lib.base.PermissionListener
import com.pmisy.roomkb.ui.viewmodel.TestViewModel
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.ActivityTestBinding
import java.io.File


/**
 * @Author : lsn
 * @CreateTime : 2023/4/10 下午 07:53
 * @Description :
 */
class TestActivity :
    BaseVersionActivity<TestViewModel, ActivityTestBinding>(R.layout.activity_test) {
    override fun getFilePath() = File(CoreConstant.getApkFile(), BuildConfig.appName + ".apk")

    var title: String = "有新版本"


    var path = "http://120.77.248.168:8888/group1/M00/00/00/wKgBj2Q3nrKEbzAbAAAAAFq5uN4278.apk"
    var desc: String = "新版本内容"
    var isFo: Boolean = true
    var code: Int = 100


    override fun initView() {
        super.initView()
    }

    override fun initEvent() {
        super.initEvent()
        binding.tvDownload.setOnClickListener {
            requestStorageGroupPermission(object : PermissionListener {
                override fun onGranted(
                    dialog: PermissionCategory,
                    permissionList: MutableList<String>?
                ) {

                    checkNewVersion(path, title, desc, isFo, code, null)
                }

                override fun onDenied(doNotAskAgain: Boolean, permissions: MutableList<String>?) {
                }

            })
        }
    }

}