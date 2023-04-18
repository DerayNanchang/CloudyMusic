package com.lsn.comm.core.exts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.lsn.lib.base.PermissionCategory
import com.lsn.lib.base.exts.showFailTips
import com.lsn.lib.utils.permission.OnPermissionCallback
import com.lsn.lib.utils.permission.Permission
import com.lsn.lib.utils.permission.XXPermissions
import com.lsn.lib.utils.util.APKUtils
import com.lsn.lib.utils.util.LogUtils
import java.io.File

/**
 * @Author : lsn
 * @CreateTime : 2023/4/13 下午 01:53
 * @Description :
 */


fun Context.installApk(path: File, authority: String) {


    XXPermissions.with(this)
        .permission(Permission.REQUEST_INSTALL_PACKAGES)
        .request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {

                APKUtils.installApk(this@installApk, path, authority)
                LogUtils.i("获取应用安装权限成功")
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                showFailTips("没有安装权限，无法安装")
            }
        })
}