package com.lsn.lib.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 03:07
 * @Description :
 */
abstract class ViewConstructor(val context: Context, private val factory: Factory) {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    private val mRootView: ViewGroup by lazy {
        createRootView()
    }

    private val mContentView: View by lazy {
        factory.create(getRootView(), mLayoutInflater)
    }


    abstract fun createRootView(): ViewGroup
    abstract fun createView(): View

    fun getFactory(): Factory = factory
    fun getRootView(): ViewGroup = mRootView
    fun getContentView(): View = mContentView

    interface Factory {

        fun create(root: ViewGroup, layoutInflater: LayoutInflater): View

    }

    class DefaultFactory(private val layoutId: Int) : Factory {

        override fun create(root: ViewGroup, layoutInflater: LayoutInflater): View {
            return layoutInflater.inflate(layoutId, root, false)
        }

    }

    class DataBindingFactory<DB : ViewDataBinding>(
        private val clazz: Class<DB>,
//        val init : (dataBinding: String) -> Unit
        val init : (dataBinding: DB) -> Unit
    ) :
        Factory {

        private lateinit var mBinding: DB

        fun getDataBinding() = mBinding

        override fun create(root: ViewGroup, layoutInflater: LayoutInflater): View {
            mBinding = clazz.getDeclaredMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            ).invoke(null, layoutInflater, root, false) as DB
            init(mBinding)
            return mBinding.root
        }

    }

    class ViewBindingFactory<VB : ViewBinding>(private val clazz: Class<VB>) : Factory {

        private lateinit var mBinding: VB

        fun getViewBinding() = mBinding

//        private inline fun <reified DB> createDB(root: ViewGroup, layoutInflater: LayoutInflater) =
//            DB::class.java.getDeclaredMethod(
//                "inflate",
//                LayoutInflater::class.java,
//                ViewGroup::class.java,
//                Boolean::class.java
//            ).invoke(null, layoutInflater, root, false) as DB


        override fun create(root: ViewGroup, layoutInflater: LayoutInflater): View {
            mBinding = clazz.getDeclaredMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            ).invoke(null, layoutInflater, root, false) as VB
            return mBinding.root
        }
    }

}