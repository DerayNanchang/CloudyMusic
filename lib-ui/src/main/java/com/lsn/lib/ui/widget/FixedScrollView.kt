package com.lsn.lib.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView


/**
 * @Author : lsn
 * @CreateTime : 2023/3/22 下午 06:53
 * @Description :
 */
class FixedScrollView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {

    init {
        isVerticalScrollBarEnabled = false
        isHorizontalFadingEdgeEnabled = false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }
}