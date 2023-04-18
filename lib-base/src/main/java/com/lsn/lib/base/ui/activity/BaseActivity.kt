package com.lsn.lib.base.ui.activity

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.base.entity.ProgressEntity
import com.lsn.lib.base.exts.hideLoadingDialogExt
import com.lsn.lib.base.exts.showFailTips
import com.lsn.lib.base.exts.showLoadingDialogExt
import com.lsn.lib.base.ui.IBUI
import com.lsn.lib.ui.utils.SnackbarUtils
import com.lsn.lib.ui.widget.dialog.DialogLoader
import com.lsn.lib.ui.widget.dialog.DownLoadDialogListener
import com.lsn.lib.ui.widget.dialog.materialdialog.GravityEnum
import com.lsn.lib.ui.widget.dialog.materialdialog.MaterialDialog
import com.lsn.lib.ui.widget.dialog.materialdialog.MaterialDialog.SingleButtonCallback
import com.lsn.lib.utils.util.KeyboardUtils
import com.lsn.lib.utils.util.MToast


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 02:55
 * @Description : 基础Activity 设置一些常用的view,dialog
 */
abstract class BaseActivity : AppCompatActivity(), IBUI {

    private var exitTime: Long = 0
    private var mDialog: MaterialDialog? = null

    protected fun showProDialog(
        title: String,
        content: String,
        process: Int,
        max: Int, isForce: Boolean,
        downLoadDialogListener: DownLoadDialogListener
    ) {
        if (mDialog == null) {
            val builder = MaterialDialog.Builder(this)
            builder.cancelable(!isForce)
            if (isForce) {
                builder.negativeText("")
            } else {
                builder.negativeText("取消")
                builder.cancelListener {
                    downLoadDialogListener.onCancel()
                }
            }
            mDialog =
                builder
                    .title(title)
                    .content(content)
                    .contentGravity(GravityEnum.START)
                    .progress(false, max, true)
                    .showListener { dialog ->
                        dialog as MaterialDialog
                        dialog.setProgress(process, downLoadDialogListener)
                        downLoadDialogListener.onProcess(dialog.maxProgress, dialog.currentProgress)
                    }.build()
            mDialog!!.show()


        } else {
            mDialog?.let {
                it.setProgress(process, downLoadDialogListener)
                it.maxProgress = max
//                it.show()
            }
        }
    }

    protected fun isShowProgress(): Boolean {
        if (mDialog == null) {
            return false
        }
        return mDialog!!.isShowing
    }


    fun show() {
        mDialog?.let {
            if (!it.isShowing) {
                mDialog?.show()
            }
        }
    }

    fun dialogDismiss() {
        mDialog?.let {
            if (it.isShowing) {
                mDialog?.dismiss()
            }
        }
    }

    fun setMPorContent(msg: String) {
        mDialog?.setContent(msg)
    }

    fun setMProgress(progress: Int, max: Int, downLoadDialogListener: DownLoadDialogListener) {
        mDialog?.setProgress(progress, downLoadDialogListener)
        mDialog?.maxProgress = max
    }

    override fun showErrorTip(msg: String?) {
        super.showTip(msg)
        showFailTips(msg)
    }


    override fun showLoadingDialog(msg: String?) {
        super.showLoadingDialog(msg)
        showLoadingDialogExt(msg)
    }

    override fun hideLoadingDialog() {
        super.hideLoadingDialog()
        hideLoadingDialogExt()
    }

    override fun showProgressDialog(progress: ProgressEntity?) {
        super.showProgressDialog(progress)

    }

    override fun hideProgressDialog() {
        super.hideProgressDialog()
    }

    override fun showConventionalTipsDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener,
    ) {
        DialogLoader.getInstance().showTipDialog(
            this,
            title,
            content,
            "确定"
        ) { dialog, _ ->
            conventionalListener.onAgree(dialog)
            dialog.dismiss()
        }
    }


    override fun showConventionalConfirmDialog(
        title: String,
        content: String,
        conventionalListener: ConventionalListener,
    ) {
        showConventionalForceDialog(title, content, false, conventionalListener)
    }

    override fun showConventionalForceDialog(
        title: String,
        content: String,
        isForce: Boolean,
        conventionalListener: ConventionalListener
    ) {
        val builder = MaterialDialog.Builder(this)
            .title(title)
            .content(content)
            .positiveText("确认")
            .onPositive { dialog, _ ->
                conventionalListener.onAgree(dialog)
                dialog.dismiss()
            }
        if (!isForce) {
            builder.negativeText("取消")
                .onNegative { dialog, _ ->
                    conventionalListener.onCancel()
                    dialog.dismiss()
                }
                .cancelable(true)
                .autoDismiss(true)
        } else {
            builder.cancelable(false)
                .autoDismiss(false)
        }.show()
    }


    override fun showToast(message: String) {
        MToast.show(message)
    }

    /**
     *  snackBar
     */
    override fun showSnackBar(view: View, msg: String) {
        if (view != null && msg != null) {
            SnackbarUtils.Short(view, msg)
        }
    }

    override fun hideKeyboard(activity: Activity) {
        KeyboardUtils.hideSoftInput(activity)
    }


    override fun showExitTips() {
        showToast("再点一次退出程序")
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isExitPage()) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                exitTime = System.currentTimeMillis()
                showExitTips()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 销毁基础 dialog
        hideLoadingDialogExt()
    }
}