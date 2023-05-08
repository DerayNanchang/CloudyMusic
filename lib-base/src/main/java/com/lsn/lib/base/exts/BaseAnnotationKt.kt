package com.lsn.lib.base.exts

import android.app.Activity
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.text.TextUtils
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.forEach
import com.lsn.lib.base.StatusColorCategory
import com.lsn.lib.base.annotation.NetListenEnabled
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.lib.base.receiver.NetConnectReceiver
import com.lsn.lib.utils.util.PStatusBarUtil
import com.lsn.lib.utils.util.SizeUtils
import com.lsn.lib.base.R
import com.lsn.lib.base.annotation.StatusBar

/**
 * @Author : lsn
 * @CreateTime : 2023/3/27 上午 09:49
 * @Description :
 */


fun Context?.addNetListenEnabled(): NetConnectReceiver? {
    var receiver: NetConnectReceiver? = null
    this?.apply {
        try {
            val clazz: Class<out Context?> = this.javaClass
            val netListenEnabled = clazz.getAnnotation(NetListenEnabled::class.java)
            netListenEnabled?.let {
                // 默认不开启网络监听
                if (netListenEnabled.value) {
                    receiver = registerNetReceiver(this)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return receiver
}

private fun registerNetReceiver(context: Context): NetConnectReceiver? {
    var receiver: NetConnectReceiver? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        // 需要代码注册
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        receiver = NetConnectReceiver()
        //注册广播接收
        context.registerReceiver(receiver, filter)
    }
    return receiver
}

fun ViewGroup?.addToolbar(): View? {

    val defHeight = 50f
    var toolbarView: View? = null
    this?.apply {
        val toolBarClazz: Class<out Context?> = context.javaClass
        toolBarClazz.getAnnotation(Toolbar::class.java)?.let { toolbar ->
            if (toolbar.isAdd) {
                toolbarView = LayoutInflater.from(context)
                    .inflate(R.layout.include_comm_toolbar, this, false)
                toolbarView?.let {
                    it.layoutParams = LinearLayout.LayoutParams(
                        matchParent,
                        SizeUtils.dp2px(defHeight)
                    )
                    addView(toolbarView)

                    val tvTitle = it.findViewById<TextView>(R.id.tvCommToolbarName)
                    if (!TextUtils.isEmpty(toolbar.title)) {
                        if (tvTitle != null) {
                            tvTitle.text = toolbar.title
                        }
                    }
                    val rlCommToolbarBack =
                        it.findViewById<RelativeLayout>(R.id.rlCommToolbarBack)
                    if (toolbar.showBack) {
                        rlCommToolbarBack.visibility = View.VISIBLE
                    } else {
                        rlCommToolbarBack.visibility = View.GONE
                    }


                    toolbar.color.apply {
                        when (this) {
                            StatusColorCategory.DEFAULT_COLOR, StatusColorCategory.WHITE_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.white))
                            }
                            StatusColorCategory.TRANSPARENT_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.transparent))
                            }
                            StatusColorCategory.BLACK_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.black))
                            }
                            StatusColorCategory.THEME_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.colorAccent))
                            }
                            StatusColorCategory.FD_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.fdfdfd))
                            }
                            StatusColorCategory.F6F5F3_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.F6F5F3))
                            }
                            StatusColorCategory.E9E9E9_COLOR -> {
                                it.setBackgroundColor(resources.getColor(com.lsn.lib.ui.R.color.e9e9e9))
                            }
                        }
                    }

                    return toolbarView
                }
            }
        }
    }
    return null
}


fun Activity?.alterStatusColor() {
    this?.apply {
        val clazz: Class<out Context> = javaClass
        val statusBarColor = clazz.getAnnotation(StatusBar::class.java)
        if (statusBarColor == null) {
            PStatusBarUtil.setResStatusBar(
                this as Activity?,
                com.lsn.lib.ui.R.color.white,
                false,
                true
            )
            return
        }
        if (!statusBarColor.isShow) {
            hideStatusBar()
        } else {
            statusBarColor?.apply {
                when (color) {
                    StatusColorCategory.DEFAULT_COLOR, StatusColorCategory.WHITE_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.white,
                        false,
                        true
                    )
                    StatusColorCategory.TRANSPARENT_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.transparent,
                        false,
                        true
                    )
                    StatusColorCategory.BLACK_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.black,
                        false,
                        false
                    )
                    StatusColorCategory.THEME_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.colorAccent,
                        false,
                        false
                    )
                    StatusColorCategory.FD_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.fdfdfd,
                        false,
                        false
                    )
                    StatusColorCategory.F6F5F3_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.F6F5F3,
                        false,
                        false
                    )
                    StatusColorCategory.E9E9E9_COLOR -> PStatusBarUtil.setResStatusBar(
                        this@alterStatusColor,
                        com.lsn.lib.ui.R.color.e9e9e9,
                        false,
                        false
                    )
                    /*else -> PStatusBarUtil.setResStatusBar(
                        context as Activity?,
                        statusBarTextColor.value,
                        false,
                        false
                    )*/
                }
            }
        }
    }
}


fun View?.addWrapView(@LayoutRes resId: Int, context: Context) {
    this?.let {
        if (this is ViewGroup) {
            this.forEach {
                if (it.tag == com.lsn.lib.ui.R.string.marker) {
                    it.parent?.let { parent ->
                        val stateGroup = LayoutInflater.from(context)
                            .inflate(resId, null, false)
                        (stateGroup as ViewGroup).addView(it)
                        (parent as ViewGroup).addView(stateGroup)
                    }
                }
            }
        }
    }
}




