package com.lsn.lib.base.ui

import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.lsn.lib.base.exts.getClazz
import com.lsn.lib.base.viewmodel.BaseViewModel

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 02:11
 * @Description :
 */
interface IViewModel<VM : BaseViewModel> {


    /**
     * ViewMode在泛型中的的位置
     */
    /**
     * ViewMode在泛型中的的位置
     * @return Int
     * @remark 如果子类中没有了[VM]泛型，则需要重写[getViewModelClass]直接指定Class
     *         例：很多界面都用的是一个[VM]，那么可以指定这个[VM]进行封装，子类重写后是没有[VM]的
     */
    fun getViewModelIndex(): Int = 0

    /**
     * ViewModel的Class类型
     * @return Class<VM>
     */
    fun getViewModelClass(): Class<VM> = getClazz(getViewModelIndex())


    /**
     * BaseViewModel添加Loading观察
     * @param viewModels Array<out BaseViewModel>
     */
    fun addLoadingObserve(vararg viewModels: BaseViewModel)




    /**
     *  获取 toolbar root view
     */
    fun getTitleRootView(): LinearLayout?

    /**
     *  获取 sub title view
     */
    fun getSubTitleView(): TextView?

    /**
     *  设置 toolbar bgc
     */
    fun setToolbarBGC(color: Int)

    /**
     *  设置 toolbar bgc
     */
    fun setToolbarBGC(color: Int, isDark: Boolean)

    /**
     *  设置 toolbar 右边的 icon
     */
    fun setEndIcon(resIcon: Int)

    /**
     *  设置 toolbar 右边的 icon
     */
    fun setEndIcon2(resIcon: Int)

    /**
     *  获取 toolbar 右边图标布局
     */
    fun getEndView(): RelativeLayout?

    /**
     *  获取 toolbar 右边图标布局
     */
    fun getEndView2(): RelativeLayout?

    /**
     *  设置 toolbar 右边文本布局
     */
    fun setEndDesc(desc: String)

    /**
     *  获取 toolbar 右边图标布局
     */
    fun getTVEndDesc(): TextView?

    /**
     *  获取 toolbar back 布局
     */
    fun getBaseBackRl(): RelativeLayout?

    /**
     *  获取 toolbar 底部 线条是否显示
     */
    fun setShowLine(isShow: Boolean)

    /**
     *  隐藏 back 布局
     */
    fun setBackGone()

    /**
     *  设置 暗色调
     */
    fun setToolbarDark()


    /**
     *  设置基本错误异常监听
     */
//    fun showErrorTipsObs()


    /**
     *  绑定Viewmodel
     */
    fun bindingViewModel()


}