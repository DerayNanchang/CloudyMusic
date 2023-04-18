package com.lsn.lib.base.ui.fragment

import com.lsn.lib.base.PermissionListener
import com.lsn.lib.base.ui.IPermissionUI
import com.lsn.lib.base.ui.activity.BasePermissionActivity


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 下午 03:12
 * @Description :
 */
abstract class BasePermissionFragment() : BaseFragment(), IPermissionUI {
    override fun requestStorageGroupPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestStorageGroupPermission(conventionalListener)
    }

    override fun requestCalendarGroupPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestCalendarGroupPermission(conventionalListener)
    }

    override fun requestContactsGroupPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestContactsGroupPermission(conventionalListener)
    }

    override fun requestBlueToothGroupPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestBlueToothGroupPermission(conventionalListener)
    }

    override fun requestCameraReadPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestCameraReadPermission(conventionalListener)
    }

    override fun requestStorageReadPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestStorageReadPermission(conventionalListener)
    }

    override fun requestStorageWritePermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestStorageWritePermission(conventionalListener)
    }

    override fun requestNotifyPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestNotifyPermission(conventionalListener)
    }

    override fun requestInstallPermission(conventionalListener: PermissionListener) {
        getBasePermissionActivity()?.requestInstallPermission(conventionalListener)
    }


    private fun getBasePermissionActivity(): BasePermissionActivity? {
        mContext?.let {
            if (it is BasePermissionActivity) {
                return it
            }
        }
        return null
    }
}