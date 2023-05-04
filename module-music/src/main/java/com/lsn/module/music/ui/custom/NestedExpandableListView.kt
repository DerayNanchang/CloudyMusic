package com.lsn.module.music.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ExpandableListView

class NestedExpandableListView : ExpandableListView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
            Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom)
        val params: ViewGroup.LayoutParams = layoutParams // 存在一个问题 ，如果是全部收起的话 ，就会导致页面空白

        params.height = measuredHeight

    }
}