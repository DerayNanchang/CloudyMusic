package com.lsn.lib.base.dialog

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StyleRes
import com.lsn.lib.ui.widget.dialog.BaseDialog
import com.lsn.lib.ui.widget.progress.loading.IMessageLoader
import com.lsn.lib.ui.widget.progress.loading.LoadingCancelListener
import com.lsn.lib.base.R


/**
 * @Author : lsn
 * @CreateTime : 2023/3/27 下午 05:57
 * @Description :
 */
class CommLoadingDialog : BaseDialog, IMessageLoader {

    private var tvTipMessage: TextView? = null
    private var mLoadingCancelListener: LoadingCancelListener? = null

    constructor(context: Context?) : super(context, R.layout.comm_dialog) {
        initView(getString(com.lsn.lib.ui.R.string.xui_tip_loading_message))
    }

    constructor(context: Context?, msg: String) : super(context, R.layout.comm_dialog) {
        initView(msg)
    }

    constructor(context: Context?, @StyleRes themeResId: Int) : super(
        context,
        R.layout.comm_dialog
    ) {
        initView(getString(com.lsn.lib.ui.R.string.xui_tip_loading_message))
    }

    constructor(context: Context?, @StyleRes themeResId: Int, msg: String) : super(
        context,
        themeResId,
        R.layout.comm_dialog
    ) {
        initView(msg)
    }


    private fun initView(tipMessage: String) {
        tvTipMessage = findViewById(R.id.tvTipMessage)
        updateMessage(tipMessage)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun updateMessage(tipMessage: String?) {

        tvTipMessage?.apply {
            if (!TextUtils.isEmpty(tipMessage)) {
                text = tipMessage
                visibility = View.VISIBLE
            } else {
                text = ""
                visibility = View.GONE
            }
        }
    }


    override fun updateMessage(tipMessageId: Int) {
        updateMessage(getString(tipMessageId))
    }

    override fun recycle() {
        if (tvTipMessage != null){
            tvTipMessage == null
        }
    }

    override fun isLoading() = isShowing;

    override fun setCancelable(flag: Boolean) {
        super.setCancelable(flag)
        if (flag) {
            setOnCancelListener {
                if (mLoadingCancelListener != null) {
                    mLoadingCancelListener!!.onCancelLoading()
                }
            }
        }
    }


    override fun setLoadingCancelListener(listener: LoadingCancelListener?) {
        mLoadingCancelListener = listener
    }


}