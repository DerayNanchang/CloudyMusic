package com.pmisy.roomkb.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.lsn.lib.ui.R


/**
 * @Author : lsn
 * @CreateTime : 2023/4/7 下午 05:11
 * @Description :
 */
open class TabContainerView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun addFocusables(views: ArrayList<View>?, direction: Int, focusableMode: Int) {
        if (!hasFocus()) {
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                if (child.visibility == View.VISIBLE
                    && child.getTag(R.id.tvTitle) as? Boolean == true
                ) {
                    views?.add(child)
                    return
                }
            }
        }
        super.addFocusables(views, direction, focusableMode)
    }
}
