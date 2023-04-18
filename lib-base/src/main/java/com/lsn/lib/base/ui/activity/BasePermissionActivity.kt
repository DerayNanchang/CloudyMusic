package com.lsn.lib.base.ui.activity

import android.os.Build
import com.lsn.lib.base.PermissionCategory
import com.lsn.lib.base.PermissionListener
import com.lsn.lib.base.ui.IPermissionUI
import com.lsn.lib.utils.permission.OnPermissionCallback
import com.lsn.lib.utils.permission.Permission
import com.lsn.lib.utils.permission.XXPermissions
import com.lsn.lib.utils.util.LogUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 08:10
 * @Description :
 */
abstract class BasePermissionActivity() : BaseActivity(), IPermissionUI {


    /**
     *  请求文件读写权限
     */
    override fun requestStorageGroupPermission(conventionalListener: PermissionListener) {
        val with = XXPermissions.with(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            with.permission(Permission.MANAGE_EXTERNAL_STORAGE)
        } else {
            with.permission(Permission.Group.STORAGE) // 设置权限请求拦截器（局部设置）
        }
        with.request(object : OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                if (!allGranted) {
                    showToast("获取部分权限成功，但部分权限未正常授予")
                    conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                    return
                }
                conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                if (doNotAskAgain) {
                    showToast("被永久拒绝授权，请手动授予文件权限")
                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                    XXPermissions.startPermissionActivity(
                        this@BasePermissionActivity,
                        permissions
                    )
                    conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                } else {
                    conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                }
            }
        })
    }


    /**
     *  请求日历读写权限
     */
    override fun requestCalendarGroupPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this) // 申请单个权限
            .permission(Permission.Group.CALENDAR) // 设置权限请求拦截器（局部设置）
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    showToast("获取日历权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予日历权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取日历权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }


    /**
     *  请求联系人读写权限
     */
    override fun requestContactsGroupPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this) // 申请单个权限
            .permission(Permission.Group.CONTACTS) // 设置权限请求拦截器（局部设置）
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予联系人权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取联系人权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }


    /**
     *  请求蓝牙读写权限
     */
    override fun requestBlueToothGroupPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this) // 申请单个权限
            .permission(Permission.Group.BLUETOOTH) // 设置权限请求拦截器（局部设置）
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予蓝牙权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取蓝牙权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }


    /**
     *  请求文件读权限
     */
    override fun requestCameraReadPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    LogUtils.i("获取相机权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予相机权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取读相机限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }


    /**
     *  请求文件读权限
     */
    override fun requestStorageReadPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this)
            .permission(Permission.READ_EXTERNAL_STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    LogUtils.i("获取读取权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予读取权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取读取权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }


    /**
     *  请求文件写权限
     */
    override fun requestStorageWritePermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this)
            .permission(Permission.WRITE_EXTERNAL_STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    //
                    LogUtils.i("获取写入权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予写入权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取写入权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })
    }

    override fun requestNotifyPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this)
            .permission(Permission.NOTIFICATION_SERVICE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    //
                    LogUtils.i("获取通知栏权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予通知栏权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取写通知栏权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })

    }

    override fun requestInstallPermission(conventionalListener: PermissionListener) {
        XXPermissions.with(this)
            .permission(Permission.REQUEST_INSTALL_PACKAGES)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予")
                        conventionalListener.onGranted(PermissionCategory.SCEPTICAL, permissions)
                        return
                    }
                    //
                    LogUtils.i("获取应用安装权限成功")
                    conventionalListener.onGranted(PermissionCategory.SUCCESS, null)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        showToast("被永久拒绝授权，请手动授予应用安装权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@BasePermissionActivity,
                            permissions
                        )
                        conventionalListener.onGranted(PermissionCategory.FREFUSE, permissions)
                    } else {
                        showToast("获取写应用安装权限失败")
                        conventionalListener.onGranted(PermissionCategory.TREFUSE, permissions)
                    }
                }
            })

    }


}