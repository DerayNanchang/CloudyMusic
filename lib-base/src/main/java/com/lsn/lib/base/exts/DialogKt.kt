package com.lsn.lib.base.exts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.base.dialog.CommLoadingDialog
import com.lsn.lib.ui.utils.WidgetUtils
import com.lsn.lib.ui.widget.dialog.DialogLoader
import com.lsn.lib.ui.widget.dialog.LoadingDialog

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 06:58
 * @Description :
 */

@SuppressLint("StaticFieldLeak")
private var commLoadingDialog: CommLoadingDialog? = null


fun Context.showConventionalDialog(
    title: String,
    content: String,
    conventionalListener: ConventionalListener,
) {
    DialogLoader.getInstance().showConfirmDialog(
        this,
        title,
        content,
        "确认", { dialog, _ ->
            conventionalListener.onAgree(dialog)
            dialog.dismiss()

        },
        "取消"
    ) { dialog, _ ->
        conventionalListener.onCancel()
        dialog.dismiss()
    }
}


fun Context?.showLoadingDialogExt(msg: CharSequence?) {
    this?.apply {
        /*if (null != commLoadingDialog) {
            hideLoadingDialogExt()
        }*/
        if (commLoadingDialog == null) {
            commLoadingDialog = createLoadingDialog(msg)
            commLoadingDialog!!.show()
        } else {
            if (!commLoadingDialog!!.isLoading) {
                commLoadingDialog?.show()
            }
        }
    }
}

fun hideLoadingDialogExt() {
    commLoadingDialog?.dismiss()
    commLoadingDialog = null
}

fun Context.showMsgTipsDialog(
    msg: CharSequence?,
    delay: Long = 1000,
    callback: (() -> Any?)? = null
) {
    showTipsDelayedDismiss(msg, 0, delay, callback)
}

fun Context.showInfoTipsDialog(
    msg: CharSequence?,
    delay: Long = 1000,
    callback: (() -> Any?)? = null
) {
    showTipsDelayedDismiss(msg, 1, delay, callback)
}

fun Context.showSuccessTipsDialog(
    msg: CharSequence?,
    delay: Long = 1000,
    callback: (() -> Any?)? = null
) {
    showTipsDelayedDismiss(msg, 2, delay, callback)
}

fun Context.showFailTipsDialog(
    msg: CharSequence?,
    delay: Long = 1000,
    callback: (() -> Any?)? = null
) {
    showTipsDelayedDismiss(msg, 3, delay, callback)
}

fun Context.showTipsDelayedDismiss(
    msg: CharSequence?,
    type: Int,
    delay: Long = 1000,
    callback: (() -> Any?)? = null
) {
    val dialog = createTipDialog(msg, type)
    Handler(Looper.getMainLooper()).postDelayed({
        dialog.dismiss()
        callback?.invoke()
    }, delay)
}

fun Context.createTipDialog(msg: CharSequence?, type: Int): LoadingDialog {
    val dialog = WidgetUtils.getLoadingDialog(this)
    dialog?.apply {
        showMsgTips(msg)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
    return dialog
}


fun Context?.createLoadingDialog(msg: CharSequence?): CommLoadingDialog? {
    this?.apply {
        val dialog = CommLoadingDialog(this)
        dialog?.apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        return dialog
    }
    return null
}