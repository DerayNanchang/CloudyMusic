package com.lsn.lib.base.loader.callback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @Author : lsn
 * @CreateTime : 2023/2/27 上午 08:16
 * @Description :
 */
abstract class Callback {

    abstract fun layoutId(): Int

    var view: View? = null

    fun ensureView(rootView: ViewGroup): View {
        if (null == view) {
            val layoutId = layoutId()
            if (layoutId != 0) {
                rootView.run {
                    view = LayoutInflater.from(context).inflate(layoutId, this, false)
                }
            } else {
                throw IllegalArgumentException("${this.javaClass.simpleName} must have a valid layoutResource")
            }
        }
        return view!!
    }
}