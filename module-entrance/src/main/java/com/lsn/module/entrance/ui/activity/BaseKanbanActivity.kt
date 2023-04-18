package com.pmisy.roomkb.ui.activity

import android.graphics.Color
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.viewmodel.BaseViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 05:07
 * @Description :
 */
abstract class BaseKanbanActivity<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseCoreActivity<VM, DB>(layoutRes) {


    override fun initView() {
        super.initView()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.navigationBarColor = Color.BLACK
    }
}