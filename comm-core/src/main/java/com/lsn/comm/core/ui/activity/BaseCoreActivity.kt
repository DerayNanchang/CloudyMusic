package com.lsn.comm.core.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.comm.core.callbacks.ICore
import com.lsn.comm.core.manager.ActivityManager
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.lib.base.ui.activity.BaseVDActivity


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:41
 * @Description :
 */
abstract class BaseCoreActivity<VM : BaseNetViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseVDActivity<VM, DB>(layoutRes),
    ICore {


    override fun initView(savedInstanceState: Bundle?) {
        ActivityManager.get().addActivity(this)
        if (isRouter()) {
            ARouter.getInstance().inject(this)
        }
    }


    override fun initData() {
        onSuccessObs()
    }

    override fun initEvent() {
        initBaseEvent()
    }

    private fun onSuccessObs() {
        viewModel.success.observe(this) {
            onSuccess(it)
        }
    }

    protected open fun onSuccess(it: ResponseEntity) {

    }

    fun isRouter(): Boolean {
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.get().finishActivity(this)
    }
}