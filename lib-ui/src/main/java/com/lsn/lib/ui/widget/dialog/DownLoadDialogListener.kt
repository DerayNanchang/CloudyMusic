package com.lsn.lib.ui.widget.dialog

/**
 * @Author : lsn
 * @CreateTime : 2023/4/10 下午 05:24
 * @Description :
 */
interface DownLoadDialogListener {

    fun onCancel()

    fun onProcess(max: Int, current: Int)

}