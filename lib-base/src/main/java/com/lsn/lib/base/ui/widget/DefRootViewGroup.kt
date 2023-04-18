package com.lsn.lib.base.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 04:12
 * @Description :
 */
class DefRootViewGroup : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}