package com.lsn.comm.core.ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lsn.comm.core.R
import com.lsn.comm.core.callbacks.ICore
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.lib.base.ui.fragment.BaseVDFragment
import com.lsn.lib.base.viewmodel.BaseViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/3/21 上午 08:53
 * @Description :
 */
abstract class BaseCoreFragment<VM : BaseNetViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseVDFragment<VM, DB>(layoutRes), ICore {

    protected var srl: SwipeRefreshLayout? = null

    override fun initView(savedInstanceState: Bundle?) {


    }

    override fun initData() {
        onSuccessObs()
    }

    override fun initEvent() {

    }

    fun setSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {
        this.srl = swipeRefreshLayout
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setColorSchemeColors(resources.getColor(com.lsn.lib.ui.R.color.themeColor))
    }


    private fun onSuccessObs() {
        viewModel.success.observe(this) {
            println(it.api)
            onSuccess(it)
        }
    }

    protected open fun onSuccess(it: ResponseEntity) {
        srl?.isRefreshing = false
    }


    private fun getBaseCoreActivity(): BaseCoreActivity<*, *>? {
        mContext?.let {
            if (it is BaseCoreActivity<*, *>) {
                return it
            }
        }
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        srl = null
    }

}