package com.pmisy.roomkb.ui.activity

import android.graphics.Color
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.lsn.comm.version.ui.activity.BaseVersionActivity
import com.lsn.lib.base.viewmodel.BaseViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/4/13 下午 04:33
 * @Description :
 */
abstract class BaseDLKanbanActivity<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseVersionActivity<VM, DB>(layoutRes) {
    override fun initView() {
        super.initView()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.navigationBarColor = Color.BLACK
    }
}