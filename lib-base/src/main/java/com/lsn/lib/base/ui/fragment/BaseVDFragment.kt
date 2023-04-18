package com.lsn.lib.base.ui.fragment

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.lsn.lib.base.BuildConfig
import com.lsn.lib.base.IDataBinding
import com.lsn.lib.base.exts.addLoadingObserve
import com.lsn.lib.base.exts.createViewModel
import com.lsn.lib.base.ui.IViewModel
import com.lsn.lib.base.ui.activity.BaseVDActivity
import com.lsn.lib.base.viewmodel.BaseViewModel
import com.lsn.lib.obs.core.OBSHelp
import com.lsn.lib.utils.util.LogUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/3/21 上午 08:52
 * @Description :
 */
abstract class BaseVDFragment<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BasePermissionFragment(),
    IDataBinding<DB>, IViewModel<VM> {

    lateinit var binding: DB

    lateinit var viewModel: VM


    override fun init(savedInstanceState: Bundle?) {
        viewContent(savedInstanceState)
    /*if (BuildConfig.DEBUG) {
            viewContent(savedInstanceState)
        } else {
            try {
                viewContent(savedInstanceState)

            } catch (e: Exception) {
                OBSHelp.instance.pushGenerateCustomLog(e)
            }
        }*/
    }

    private fun viewContent(savedInstanceState: Bundle?) {
        viewModel = createViewModel(this)
        addLoadingObserve(viewModel)
        initView(savedInstanceState)
        initData()
        initEvent()
        bindingViewModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.bind(buildContentView())!!
        return binding.root
    }


    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()
    abstract fun initEvent()


    private fun buildContentView(): View {
//        val viewRoot = LayoutInflater.from(this).inflate(R.layout.include_root, null, false)
/*        if (viewRoot is ViewGroup) {
            viewRoot.apply {
               addToolbar()
            }
        }*/
        return LayoutInflater.from(context).inflate(layoutRes, null, false)
    }

    override fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        addLoadingObserve(this, *viewModels)
    }

    override fun bindingViewModel() {
        binding.run {
            this.setVariable(BR.vm, viewModel)
            SparseArray<Any>().apply {
                forEach { key, any ->
                    setVariable(key, any)
                }
            }.clear()
        }
    }


    override fun getTitleRootView(): LinearLayout? {
        return getBaseVDActivity()?.getTitleRootView()
    }

    override fun getSubTitleView(): TextView? {
        return getBaseVDActivity()?.getSubTitleView()
    }

    override fun setToolbarBGC(color: Int) {
        getBaseVDActivity()?.setToolbarBGC(color)
    }

    override fun setToolbarBGC(color: Int, isDark: Boolean) {
        getBaseVDActivity()?.setToolbarBGC(color, isDark)
    }

    override fun setEndIcon(resIcon: Int) {
        getBaseVDActivity()?.setEndIcon(resIcon)
    }

    override fun setEndIcon2(resIcon: Int) {
        getBaseVDActivity()?.setEndIcon2(resIcon)
    }

    override fun getEndView(): RelativeLayout? {
        return getBaseVDActivity()?.getEndView()
    }

    override fun getEndView2(): RelativeLayout? {
        return getBaseVDActivity()?.getEndView2()
    }

    override fun setEndDesc(desc: String) {
        getBaseVDActivity()?.setEndDesc(desc)
    }

    override fun getTVEndDesc(): TextView? {
        return getBaseVDActivity()?.getTVEndDesc()
    }

    override fun getBaseBackRl(): RelativeLayout? {
        return getBaseVDActivity()?.getBaseBackRl()
    }

    override fun setShowLine(isShow: Boolean) {
        getBaseVDActivity()?.setShowLine(isShow)
    }

    override fun setBackGone() {
        getBaseVDActivity()?.setBackGone()
    }

    override fun setToolbarDark() {
        getBaseVDActivity()?.setToolbarDark()
    }


    private fun getBaseVDActivity(): BaseVDActivity<*, *>? {
        mContext?.let {
            if (it is BaseVDActivity<*, *>) {
                return it
            }
        }
        return null
    }


}