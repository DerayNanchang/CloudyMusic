package com.lsn.comm.core.viewmodel

import androidx.lifecycle.viewModelScope
import com.lsn.comm.core.exts.launch
import com.lsn.comm.core.exts.msg
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.lib.base.bus.LiveBus
import com.lsn.lib.base.livedata.ProtectedUnPeekLiveData
import com.lsn.lib.base.livedata.UnPeekLiveData
import com.lsn.lib.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope


/**
 * @Author : lsn
 * @CreateTime : 2023/3/8 上午 08:26
 * @Description :
 */
open abstract class BaseNetViewModel : BaseViewModel() {

    /**
     * 请求返回的数据
     */
    private val suc: UnPeekLiveData<ResponseEntity> =
        UnPeekLiveData.Builder<ResponseEntity>().setAllowNullValue(true).create()

    /**
     * 请求无论成功或者失败之后的回调
     */
    private val fin: UnPeekLiveData<Boolean> = UnPeekLiveData()


    /**
     * 向 ui 层提供 ProtectedUnPeekLiveData，从而限制从 Activity/Fragment 篡改来自 "数据层" 的数据，数据层的数据务必通过 "唯一可信源" 来分发
     * from: KunMinX  https://xiaozhuanlan.com/topic/0168753249
     */
    open var success: ProtectedUnPeekLiveData<ResponseEntity> =
        suc as ProtectedUnPeekLiveData<ResponseEntity>
    open var finally: ProtectedUnPeekLiveData<Boolean> = fin as ProtectedUnPeekLiveData<Boolean>


    /**
     * 请求成功后设置数据调用此方法
     * @param response
     */
    protected fun onSuccess(response: ResponseEntity) {
        this.suc.value = response
    }

    /**
     * 请求错误时调用此方法
     * @param errorMsg 错误信息
     * @param errorLiveData 错误接收的LiveData
     */
    protected fun onError(errorMsg: String?, mErrorLiveData: UnPeekLiveData<String>? = null) {
        (mErrorLiveData ?: netErrorMsgLiveData).value = errorMsg
    }

    protected fun onFinally() {
        fin.value = true
    }

    /**
     * 请求
     * @param block 请求的主函数体，得到数据后调用onSuccess方法
     * @param loadingMsg 请求时的提示语句，不为空时才开启弹窗提示
     * @param errorLiveData 接收错误的LiveData
     */
    protected fun BaseNetViewModel.request(
        block: suspend CoroutineScope.() -> Unit,
        loadingMsg: String = "数据正在加载中...",
        isShowDialog: Boolean = true,
        errorLiveData: UnPeekLiveData<String>? = null
    ) {
        launch({
            block()
        }, {
            // 错误回调
            // 这里可以给不同的请求设置不同的错误接收
            it.printStackTrace()
            if (errorLiveData == null) {
                onError(it.msg, netErrorMsgLiveData)
            } else {
                onError(it.msg, errorLiveData)
            }
        }, {
            // 请求开始
            // 求时的提示语句不为空时才开启弹窗提示
            loadingMsg?.let {
                mShowLoadingDialog(it)
            }
        }, {
            // 请求结束
            onFinally()
            // 关闭loading
            loadingMsg?.let {
                mHideLoadingDialog()
            }
        }
        )
    }
}