package com.lsn.comm.core.exts

import android.annotation.SuppressLint
import android.app.Activity
import com.lsn.comm.core.callbacks.OnKeyBackClickListener
import com.lsn.lib.base.entity.ProgressEntity
import com.lsn.lib.ui.utils.WidgetUtils
import com.lsn.lib.ui.widget.dialog.LoadingDialog

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:54
 * @Description :
 */



@SuppressLint("StaticFieldLeak")
private var progressDialog: LoadingDialog? = null
fun Activity.showProgressDialog(data: ProgressEntity) {
    with(data) {
        if (null == progressDialog) {
            progressDialog = WidgetUtils.getLoadingDialog(this@showProgressDialog)
            progressDialog?.apply {
                setCanceledOnTouchOutside(outSideCancel)
                setOnKeyListener(OnKeyBackClickListener())
            }
            progressDialog?.run {
                updateMessage(msg)
                if (!isShowing) {
                    show()
                }
            }
        }
    }
}

fun hideProgressDialogExt() {
    progressDialog?.dismiss()
    progressDialog = null
}