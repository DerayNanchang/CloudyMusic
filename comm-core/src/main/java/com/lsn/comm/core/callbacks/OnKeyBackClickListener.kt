package com.lsn.comm.core.callbacks

import android.content.DialogInterface
import android.view.KeyEvent


/**
 * @Author : lsn
 * @CreateTime : 2023/3/21 上午 09:32
 * @Description :
 */
class OnKeyBackClickListener(private val isForce:Boolean = true): DialogInterface.OnKeyListener {

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        return keyCode == KeyEvent.KEYCODE_BACK && event!!.repeatCount == 0 && isForce
    }

}