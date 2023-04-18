package com.lsn.lib.base

import android.content.DialogInterface


/**
 * Author: lsn
 * Date: 2022/1/4
 * Description
 */
interface ConventionalListener {

    fun onAgree(dialog: DialogInterface)

    fun onCancel()

}