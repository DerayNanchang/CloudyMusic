package com.lsn.comm.core.exts

import androidx.lifecycle.viewModelScope
import com.lsn.lib.base.viewmodel.BaseViewModel
import kotlinx.coroutines.*

/**
 * @Author : lsn
 * @CreateTime : 2023/3/22 上午 10:30
 * @Description :
 */
/**
 * @param block     协程代码块，运行在UI线程
 * @param onError   异常回调，运行在UI线程
 * @param onStart   协程开始回调，运行在UI线程
 * @param onFinally 协程结束回调，不管成功/失败，都会回调，运行在UI线程
 */
fun BaseViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onFinally: (() -> Unit)? = null
): Job {
    return viewModelScope.launch {
        try {
            coroutineScope {
                onStart?.invoke()
                block()
            }
        } catch (e: Throwable) {
            if (onError != null && isActive) {
                try {
                    onError(e)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            } else {
                e.printStackTrace()
            }
        } finally {
            onFinally?.invoke()
        }
    }
}
