package com.lsn.comm.core.ui.activity

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.comm.core.callbacks.ICore
import com.lsn.comm.core.manager.ActivityManager
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.lib.base.ui.activity.BaseVDActivity
import com.lsn.lib.base.viewmodel.BaseViewModel
import com.lsn.lib.base.R


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:41
 * @Description :
 */
abstract class BaseCoreActivity<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseVDActivity<VM, DB>(layoutRes),
    ICore {


    override fun initView() {
        ActivityManager.get().addActivity(this)
        if (isRouter()){
//            ARouter.getInstance().inject(this);
        }
    }


    override fun initData() {
        onResponseReceiver()
    }

    override fun initEvent() {
    }

    protected open fun onResponseReceiver() {

    }

    fun isRouter():Boolean{
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.get().finishActivity(this)
    }
}