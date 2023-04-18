package com.pmisy.roomkb.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout


/**
 * @Author : lsn
 * @CreateTime : 2023/4/7 下午 06:16
 * @Description :
 */
class FocusFixRelativeLayout : RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun clearFocus() {
        if (this.parent != null)
        super.clearFocus()
    }
}