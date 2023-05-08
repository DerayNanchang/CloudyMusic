package com.lsn.lib.base.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.lsn.lib.base.exts.*
import com.lsn.lib.base.ui.IViewModel
import com.lsn.lib.base.viewmodel.BaseViewModel
import com.lsn.lib.utils.util.LogUtils
import com.lsn.lib.base.R
import com.lsn.lib.obs.core.OBSHelp


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:14
 * @Description :
 */
abstract class BaseVDActivity<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private var layoutRes: Int) :
    BaseNotificationActivity(), IDataBinding<DB>, IViewModel<VM> {

    lateinit var binding: DB

    lateinit var viewModel: VM

    protected var toolbarRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }

        viewContent(savedInstanceState)

        /*if (BuildConfig.DEBUG){
                try {
                    viewContent()
                } catch (e: Exception) {
                    OBSHelp.instance.pushGenerateCustomLog(e)
                }
            }else{
                try {
                    viewContent()
                } catch (e: Exception) {
                    OBSHelp.instance.pushGenerateCustomLog(e)
                }
            }*/
    }

    private fun viewContent(savedInstanceState: Bundle?) {
//        binding = DataBindingUtil.bind(buildContentView())!!

        val linearLayout = LinearLayout(this)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutRes, linearLayout, false)
        linearLayout.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        linearLayout.orientation = LinearLayout.VERTICAL
        toolbarRootView = linearLayout.addToolbar()
        linearLayout.addView(binding.root)
        setContentView(linearLayout)
        alterStatusColor()
        viewModel = createViewModel(this)
        // 放在[initView]之前，不然错误的在[initView]方法里请求了，响应太快[createObserver]都没还没执行
        addLoadingObserve(viewModel)
        initView(savedInstanceState)
        initData()
        initEvent()
        bindingViewModel()
    }


    private fun buildContentView(): View {
        return LayoutInflater.from(this).inflate(layoutRes, null, false)
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

    protected open fun isBaseFinish(): Boolean {
        return true
    }

    protected fun initBaseEvent() {
        toolbarRootView?.apply {
            if (isBaseFinish()) {
                findViewById<View>(R.id.rlCommToolbarBack).setOnClickListener {
                    finish()
                }
            }
        }
    }


    override fun getTitleRootView(): LinearLayout? {
      return  toolbarRootView?.findViewById(R.id.llCommTitleRoot)
    }

    override fun getSubTitleView(): TextView? {
      return  toolbarRootView?.findViewById(R.id.tvCommSubToolbarName)
    }

    override fun setToolbarBGC(color: Int) {
        toolbarRootView?.apply {
            val rlToolbarRoot = findViewById<RelativeLayout>(R.id.rlToolbarRoot)
            rlToolbarRoot.setBackgroundResource(color)
        }
    }

    override fun setToolbarBGC(color: Int, isDark: Boolean) {
        setToolbarBGC(color)
        if (isDark) {
            setToolbarDark()
        }
    }

    override fun setEndIcon(resIcon: Int) {
        toolbarRootView?.apply {
            val ivInfoMore = findViewById<ImageView>(R.id.ivInfoMore)
            if (ivInfoMore != null) {
                ivInfoMore.setImageResource(resIcon)
                ivInfoMore.visibility = View.VISIBLE
            }
        }
    }

    override fun setEndIcon2(resIcon: Int) {
        toolbarRootView?.apply {
            val ivInfoMore = findViewById<ImageView>(R.id.ivInfoMore2)
            ivInfoMore.setImageResource(resIcon)
            ivInfoMore.visibility = View.VISIBLE
        }
    }

    override fun getEndView(): RelativeLayout? {
        return toolbarRootView?.findViewById(R.id.rlEndViewRoot)
    }

    override fun getEndView2(): RelativeLayout? {
        return toolbarRootView?.findViewById(R.id.rlEndViewRoot2)
    }

    override fun setEndDesc(desc: String) {
        toolbarRootView?.apply {
            val tvInfoMore = findViewById<TextView>(R.id.tvInfoMore)
            tvInfoMore.text = desc
            tvInfoMore.visibility = View.VISIBLE
        }
    }


    override fun setBaseTitle(title: String) {
        toolbarRootView?.apply {
            val tvName = findViewById<TextView>(R.id.tvCommToolbarName)
            tvName.text = title
            tvName.visibility = View.VISIBLE
        }
    }

    override fun getTVEndDesc(): TextView? {
        return toolbarRootView?.findViewById(R.id.tvInfoMore)
    }

    override fun getBaseBackRl(): RelativeLayout? {
        return toolbarRootView?.findViewById(R.id.rlCommToolbarBack)
    }

    override fun setShowLine(isShow: Boolean) {
        if (isShow) {
            toolbarRootView?.apply {
                val vToolbarLine = findViewById<View>(R.id.vToolbarLine)
                vToolbarLine.visibility = View.VISIBLE
            }
        }
    }

    override fun setBackGone() {
        toolbarRootView?.apply {
            val rlCommToolbarBack = findViewById<RelativeLayout>(R.id.rlCommToolbarBack)
            rlCommToolbarBack.visibility = View.GONE
        }
    }

    override fun setToolbarDark() {
        toolbarRootView?.apply {
            val ivInfoBack = findViewById<ImageView>(R.id.ivInfoBack)
            val tvCommToolbarName = findViewById<TextView>(R.id.tvCommToolbarName)
            ivInfoBack.setImageResource(com.lsn.lib.ui.R.drawable.ic_svg_back_b)
            tvCommToolbarName.setTextColor(resources.getColor(com.lsn.lib.ui.R.color.white))
        }
    }


    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()
    abstract fun initEvent()
}