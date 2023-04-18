package com.lsn.lib.base.ui.fragment

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.base.ui.IBUI
import com.lsn.lib.base.ui.activity.BaseActivity


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 04:01
 * @Description :
 */
abstract class BaseFragment : BaseLazyFragment(), IBUI {
    protected var mContext: Context? = null
    lateinit var mActivity: AppCompatActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }


    override fun showConventionalTipsDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener
    ) {
        getBaseActivity()?.showConventionalTipsDialog(title, content, conventionalListener)
    }

    override fun showConventionalConfirmDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener
    ) {
        getBaseActivity()?.showConventionalConfirmDialog(title, content, conventionalListener)
    }

    override fun showConventionalForceDialog(
        title: String,
        content: String,
        isForce: Boolean,
        conventionalListener: ConventionalListener
    ) {
        getBaseActivity()?.showConventionalForceDialog(title, content, isForce, conventionalListener)
    }

    override fun showToast(message: String) {
        getBaseActivity()?.showToast(message)
    }

    override fun showSnackBar(view: View, msg: String) {
        getBaseActivity()?.showSnackBar(view, msg)
    }

    override fun hideKeyboard(activity: Activity) {
        getBaseActivity()?.hideKeyboard(activity)
    }

    override fun showExitTips() {
        getBaseActivity()?.showExitTips()
    }

    private fun getBaseActivity(): BaseActivity? {
        mContext?.let {
            if (it is BaseActivity) {
                return it
            }
        }
        return null
    }

}