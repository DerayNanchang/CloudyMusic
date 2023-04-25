package com.lsn.module.entrance.adapter

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.lsn.lib.ui.widget.TypeTextView


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 上午 10:52
 * @Description :
 */


object WelcomeAdapter {


    @BindingAdapter("ttext")
    @JvmStatic
    fun setTypeText(typeTextView: TypeTextView, text: String?) {
        if (!TextUtils.isEmpty(text)){
            typeTextView.start(text, 120)
        }
    }

}