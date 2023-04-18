package com.lsn.lib.base.uitls

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import com.lsn.lib.ui.XUI
import com.lsn.lib.ui.widget.toast.XToast


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 06:48
 * @Description :
 */
object XToastUtils {

    private val DEFAULT_ALPHA = 200

    private val MAIN_HANDLER = Handler(Looper.getMainLooper())

    private fun XToastUtils() {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    @MainThread
    fun toast(message: CharSequence) {
        showToast(XToast.normal(XUI.getContext(), message))
    }

    @MainThread
    fun toast(@StringRes message: Int) {
        showToast(XToast.normal(XUI.getContext(), message))
    }

    @MainThread
    fun toast(message: CharSequence, duration: Int) {
        showToast(XToast.normal(XUI.getContext(), message, duration))
    }

    @MainThread
    fun toast(@StringRes message: Int, duration: Int) {
        showToast(XToast.normal(XUI.getContext(), message, duration))
    }

    //=============//

    //=============//
    @MainThread
    fun error(message: CharSequence) {
        showToast(XToast.error(XUI.getContext(), message))
    }

    @MainThread
    fun error(error: Exception) {
        val message = if (error.message != null) error.message else ""
        showToast(XToast.error(XUI.getContext(), message.toString()))
    }

    @MainThread
    fun error(@StringRes message: Int) {
        showToast(XToast.error(XUI.getContext(), message))
    }

    @MainThread
    fun error(message: CharSequence, duration: Int) {
        showToast(XToast.error(XUI.getContext(), message, duration))
    }

    @MainThread
    fun error(@StringRes message: Int, duration: Int) {
        showToast(XToast.error(XUI.getContext(), message, duration))
    }

    //=============//

    //=============//
    @MainThread
    fun success(message: CharSequence) {
        showToast(XToast.success(XUI.getContext(), message))
    }

    @MainThread
    fun success(@StringRes message: Int) {
        showToast(XToast.success(XUI.getContext(), message))
    }

    @MainThread
    fun success(message: CharSequence, duration: Int) {
        showToast(XToast.success(XUI.getContext(), message, duration))
    }

    @MainThread
    fun success(@StringRes message: Int, duration: Int) {
        showToast(XToast.success(XUI.getContext(), message, duration))
    }

    //=============//

    //=============//
    @MainThread
    fun info(message: CharSequence) {
        showToast(XToast.info(XUI.getContext(), message))
    }

    @MainThread
    fun info(@StringRes message: Int) {
        showToast(XToast.info(XUI.getContext(), message))
    }

    @MainThread
    fun info(message: CharSequence, duration: Int) {
        showToast(XToast.info(XUI.getContext(), message, duration))
    }

    @MainThread
    fun info(@StringRes message: Int, duration: Int) {
        showToast(XToast.info(XUI.getContext(), message, duration))
    }

    //=============//

    //=============//
    @MainThread
    fun warning(message: CharSequence) {
        showToast(XToast.warning(XUI.getContext(), message))
    }

    @MainThread
    fun warning(@StringRes message: Int) {
        showToast(XToast.warning(XUI.getContext(), message))
    }

    @MainThread
    fun warning(message: CharSequence, duration: Int) {
        showToast(XToast.warning(XUI.getContext(), message, duration))
    }

    @MainThread
    fun warning(@StringRes message: Int, duration: Int) {
        showToast(XToast.warning(XUI.getContext(), message, duration))
    }

    private fun showToast(toast: Toast) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            toast.show()
        } else {
            MAIN_HANDLER.post { toast.show() }
        }
    }
}