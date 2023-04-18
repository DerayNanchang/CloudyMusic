package com.lsn.comm.core.ui.fragment

import android.os.Bundle
import android.util.SparseArray
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.lsn.comm.core.callbacks.ICore
import com.lsn.comm.core.manager.ActivityManager
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.ui.fragment.BaseVDFragment
import com.lsn.lib.base.viewmodel.BaseViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/3/21 上午 08:53
 * @Description :
 */
abstract class BaseCoreFragment<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseVDFragment<VM, DB>(layoutRes), ICore {


    override fun initView(savedInstanceState: Bundle?) {
        onResponseReceiver()
    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    protected open fun onResponseReceiver() {

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