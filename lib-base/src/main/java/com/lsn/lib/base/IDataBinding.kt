package com.lsn.lib.base

import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.lsn.lib.base.exts.getClazz

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:20
 * @Description :
 */
interface IDataBinding<DB : ViewDataBinding> {


    /**
     * DataBinding在泛型中的位置
     * @return Int
     * @remark 如果子类中没有了DB泛型，则需要重写[getDataBindingClass]直接指定Class
     *         例：很多界面都用的是一个DB，那么可以指定这个DB进行封装，子类重写后是没有DB的
     */
    fun getDataBindingIndex(): Int = 1

    /**
     * DataBinding的class
     * @return Class<*>
     */
    fun getDataBindingClass(): Class<DB> = getClazz(getDataBindingIndex())


    /**
     * 视图绑定里的Click
     * @return Any?
     */
    fun getBindingClick(): Any? = null

    /**
     * 向DataBinding注入值
     * @receiver SparseArray<Any>
     *
     * 示例:
     *
     *   addParams(BR.item,data)
     *
     */
    fun SparseArray<Any>.applyBindingParams() {}
}