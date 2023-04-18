package com.lsn.lib.base.ui

import com.lsn.lib.base.PermissionListener

/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 下午 03:15
 * @Description :
 */
interface IPermissionUI {

    fun requestStorageGroupPermission(conventionalListener: PermissionListener)


    fun requestCalendarGroupPermission(conventionalListener: PermissionListener)



    fun requestContactsGroupPermission(conventionalListener: PermissionListener)


    fun requestBlueToothGroupPermission(conventionalListener: PermissionListener)


    fun requestCameraReadPermission(conventionalListener: PermissionListener)


    fun requestStorageReadPermission(conventionalListener: PermissionListener)


    fun requestStorageWritePermission(conventionalListener: PermissionListener)


    fun requestNotifyPermission(conventionalListener: PermissionListener)


    fun requestInstallPermission(conventionalListener: PermissionListener)

}