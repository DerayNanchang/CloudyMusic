package com.lsn.lib.base.loader

import android.view.View


/**
 * @Author : lsn
 * @CreateTime : 2023/2/27 上午 08:17
 * @Description :
 */
class LoaderReplaceService: LoaderService() {

    override fun showSuccessView(view: View) {
        showLoaderView(view)
    }

    override fun showLoaderView(view: View) {
        with(ensureRootView()) {
            preView?.let {
                // 设置id,解决ConstraintLayout布局问题
                view.id =  it.id
                val index = indexOfChild(it)
                removeViewInLayout(it)
                addView(view, index, successCallback?.view?.layoutParams)
                // 某些机型addView后不执行此方法，这里手动执行下
                requestApplyInsets()
            }
            preView = view
        }
    }

}