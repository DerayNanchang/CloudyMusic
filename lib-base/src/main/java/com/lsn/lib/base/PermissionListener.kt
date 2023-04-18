package com.lsn.lib.base


/**
 * Author: lsn
 * Date: 2022/1/4
 * Description
 */
interface PermissionListener {

    fun onGranted(dialog: PermissionCategory, permissionList: MutableList<String>?)

    fun onDenied(doNotAskAgain: Boolean, permissions: MutableList<String>?)

}