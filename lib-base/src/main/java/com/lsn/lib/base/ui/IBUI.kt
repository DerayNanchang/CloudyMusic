package com.lsn.lib.base.ui

import android.app.Activity
import android.view.View
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.base.entity.ProgressEntity

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:06
 * @Description :
 */
interface IBUI {


    fun showTip(msg: String?){

    }

    fun showErrorTip(msg: String?){}


    /**
     * 显示加载框
     * @param msg String?
     */
    fun showLoadingDialog(msg: String?) {}

    /**
     * 隐藏加载框
     */
    fun hideLoadingDialog() {}

    /**
     * 显示进度弹窗
     * @param progress ProgressBean
     */
    fun showProgressDialog(progress: ProgressEntity?) {}

    /**
     * 隐藏进度弹窗
     */
    fun hideProgressDialog() {}



    fun showConventionalTipsDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener,
    )

    fun showConventionalConfirmDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener,
    )

    fun showConventionalForceDialog(
        title: String,
        content: String,
        isForce: Boolean,
        conventionalListener: ConventionalListener,
    )

    fun showToast(message: String)


    fun showSnackBar(view: View, msg: String)


    fun hideKeyboard(activity: Activity)


    /**
     * 是否为退出界面
     * @return Boolean
     */
    fun isExitPage(): Boolean = false

    /**
     * 显示退出提示
     */
    fun showExitTips()

}