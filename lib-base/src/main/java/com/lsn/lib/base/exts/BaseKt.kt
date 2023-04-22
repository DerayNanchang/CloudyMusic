package com.lsn.lib.base.exts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lsn.lib.base.ui.IBUI
import com.lsn.lib.base.ui.IViewModel
import com.lsn.lib.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import java.util.Objects

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 02:51
 * @Description :
 */

/**
 * 获取当前类绑定的泛型clazz
 */
@Suppress("UNCHECKED_CAST")
fun <T> Any.getClazz(index: Int = 0): T {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[index] as T
}


fun <VM : BaseViewModel> IViewModel<VM>.createViewModel(owner: ViewModelStoreOwner): VM =
    ViewModelProvider(owner)[getViewModelClass()]


fun IBUI.addLoadingObserve(
    owner: LifecycleOwner,
    vararg viewModels: BaseViewModel
) {
    viewModels.forEach { viewModel ->
        with(viewModel) {
            //显示弹窗
            getShowLoadingLiveData().observe(owner) {
                showLoadingDialog(it)
            }
            //关闭弹窗
            getHideLoadingLiveData().observe(owner) {
                hideLoadingDialog()
            }
            getShowProgressLiveData().observe(owner) {
                showProgressDialog(it)
            }
            getHideProgressLiveData().observe(owner) {
                hideProgressDialog()
            }

            getErrorMsgLiveData().observe(owner) {
                if (it == null) {
                    showErrorTip("返回错误异常")
                } else {
                    showErrorTip(it)
                }
            }
        }
    }
}



