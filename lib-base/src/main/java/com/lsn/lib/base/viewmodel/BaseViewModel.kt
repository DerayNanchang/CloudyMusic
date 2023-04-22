package com.lsn.lib.base.viewmodel

import androidx.lifecycle.ViewModel
import com.lsn.lib.base.entity.ProgressEntity
import com.lsn.lib.base.livedata.UnPeekLiveData


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 02:12
 * @Description :
 */
open abstract class BaseViewModel : ViewModel() {

    protected val TAG: String = this.javaClass.simpleName

    private val uiChange: UiChange by lazy { UiChange() }

    var netErrorMsgLiveData: UnPeekLiveData<String> =
        UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()

    inner class UiChange {
        //显示加载框
        val showLoading by lazy { UnPeekLiveData<String>() }

        //隐藏
        val hideLoading by lazy { UnPeekLiveData<Boolean>() }

        //显示进度弹窗
        val showProgress by lazy { UnPeekLiveData<ProgressEntity>() }

        // 隐藏进度弹窗
        val hideProgress by lazy { UnPeekLiveData<Boolean>() }
    }

    // 供子类和外部调用

    fun getShowLoadingLiveData(): UnPeekLiveData<String> = uiChange.showLoading
    fun getHideLoadingLiveData(): UnPeekLiveData<Boolean> = uiChange.hideLoading
    fun getShowProgressLiveData(): UnPeekLiveData<ProgressEntity> = uiChange.showProgress
    fun getHideProgressLiveData(): UnPeekLiveData<Boolean> = uiChange.hideProgress


    fun getErrorMsgLiveData(): UnPeekLiveData<String> = netErrorMsgLiveData


    /**
     * 显示加载弹窗
     * @param msg String?
     */
    open fun mShowLoadingDialog(msg: String) {
        uiChange.showLoading.value = msg
    }

    /**
     * 隐藏加载弹窗
     */
    open fun mHideLoadingDialog() {
        uiChange.hideLoading.value = true
    }

    /**
     * 显示进度弹窗
     * @param msg String? 提示语
     * @param percent Int 当前进度
     * @param max Int 总进度
     * @param outSideCancel Boolean 点击外部是否能关闭
     * @param keyBackCancel Boolean 点击返回键是否能关闭
     */
    open fun showProgressDialog(
        msg: String? = "",
        percent: Int = 0,
        max: Int = 100,
        outSideCancel: Boolean = false,
        keyBackCancel: Boolean = false
    ) {
        uiChange.showProgress.value =
            ProgressEntity(msg, percent, max, outSideCancel, keyBackCancel)
    }

    /**
     * 隐藏进度弹窗
     */
    open fun hideProgressDialog() {
        uiChange.hideProgress.value = true
    }

    open fun showNetFailTips(msg: String) {
        netErrorMsgLiveData.value = msg
    }

}