package com.lsn.comm.core.ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
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


    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData() {
        onSuccessObs()
    }

    override fun initEvent() {

    }

    private fun onSuccessObs() {
        viewModel.success.observe(this) {
            println(it.api)
            onSuccess(it)
        }
    }

    protected open fun onSuccess(it: ResponseEntity) {

    }


    private fun getBaseCoreActivity(): BaseCoreActivity<*, *>? {
        mContext?.let {
            if (it is BaseCoreActivity<*, *>) {
                return it
            }
        }
        return null
    }

}