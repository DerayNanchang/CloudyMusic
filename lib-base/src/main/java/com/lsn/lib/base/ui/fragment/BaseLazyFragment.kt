package com.lsn.lib.base.ui.fragment


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 下午 02:31
 * @Description :
 */

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 04:01
 * @Description :
 */
abstract class BaseLazyFragment : Fragment() {

    private var isFmtVisible = false // fragment 是否可视（by 懒加载）
    private var isPrepared = false // 是否准备好了  (by 懒加载1)
    private var isFirst = true


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true // 会先执行 setUserVisibleHint 方法再执行 onActivityCreated 为了避免空指针需要做个标记
        if (isLazyLoad()) {
            lazyLoadValid(savedInstanceState)
        } else {
            // 不使用懒加载
            baseInit(savedInstanceState)
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isLazyLoad()) {
            isFmtVisible = userVisibleHint
            lazyLoadValid(null)
        }
    }


    private fun lazyLoadValid(savedInstanceState: Bundle?) {
        if (isFmtVisible && isPrepared && isFirst) {
            isFirst = false
            baseInit(savedInstanceState)
        }
    }

    private fun baseInit(savedInstanceState: Bundle?) {
        if (activity != null) {
            if (isLazyLoad()) {
//                showDialog()
            }
            init(savedInstanceState)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        if (isLazyLoad()) {
            isFmtVisible = false
            isPrepared = false
            isFirst = true
        }
    }

    open fun isLazyLoad(): Boolean {
        return false
    }

    protected abstract fun init(savedInstanceState: Bundle?)


    protected fun isInjectARouter(): Boolean {
        return true
    }
}