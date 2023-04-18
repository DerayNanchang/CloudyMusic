package com.lsn.lib.base.exts

import android.content.Context
import com.lsn.lib.base.uitls.XToastUtils

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 05:07
 * @Description :
 */
fun showMsgTips(msg: CharSequence?) {
    showTips(msg, 0)
}

fun showInfoTips(msg: CharSequence?) {
    showTips(msg, 1)
}

fun showSuccessTips(msg: CharSequence?) {
    showTips(msg, 2)
}

fun showFailTips(msg: CharSequence?) {
    showTips(msg, 3)
}

fun showWarningTips(msg: CharSequence?) {
    showTips(msg, 4)
}

fun showTips(msg: CharSequence?, type: Int) {
    msg?.let {
        when (type) {
            0 -> XToastUtils.toast(it)
            1 -> XToastUtils.info(it)
            2 -> XToastUtils.success(it)
            3 -> XToastUtils.error(it)
            4 -> XToastUtils.warning(it)
            else -> {}
        }
    }
}