package com.lsn.comm.core.exts

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
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


/**
 * 延迟任务
 * @param time Long 延迟时间
 * @param action Function0<Unit>
 */
fun delay(time: Long, action: () -> Unit) {
    // 获取当前线程Looper，如果是主线程肯定不会为空
    val looper = Looper.myLooper()
    // 如果是主线程直接使用主线程的Looper
    if (looper == Looper.getMainLooper()) {
        Handler(Looper.getMainLooper()).postDelayed(action, time)
        return
    }
    // 如果没有开启Looper，需要这里处理开启和循环和退出
    val noPrepare = null == looper
    if (noPrepare) {
        Looper.prepare()
    }
    // 这里的Looper就一定不为空了
    Looper.myLooper()?.let {
        Handler(it).postDelayed({
            action.invoke()
            if (noPrepare){
                it.quitSafely()
            }
        }, time)
    }
    // 开起消息循环
    if (noPrepare)
        Looper.loop()
}

