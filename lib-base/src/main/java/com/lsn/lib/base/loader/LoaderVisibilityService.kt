package com.lsn.lib.base.loader

import android.view.View
import android.view.View.GONE
import android.view.View.NO_ID


/**
 * @Author : lsn
 * @CreateTime : 2023/2/27 上午 08:18
 * @Description :
 */
class LoaderVisibilityService : LoaderService() {

    override fun showSuccessView(view: View) {
        preView?.let {
            ensureRootView().removeViewInLayout(it)
        }
        view.run {
            id = loaderId
            visibility = View.VISIBLE
        }
    }

    override fun showLoaderView(view: View) {
        with(ensureRootView()) {
            successCallback?.view?.run {
                if (preView == this) {
                    preView = null
                }
                id = NO_ID
                visibility = GONE
            }
            var index = 0
            // 设置id,解决ConstraintLayout布局问题
            view.id = loaderId
            preView?.let {
                index = indexOfChild(it)
                removeViewInLayout(it)
            }
            if (index > 0) {
                addView(view, index, loaderParams)
            } else {
                addView(view, loaderParams)
            }
            preView = view
        }
    }

}