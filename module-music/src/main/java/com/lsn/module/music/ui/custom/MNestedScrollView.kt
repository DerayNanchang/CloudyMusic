package com.lsn.module.music.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.lsn.module.music.R

class MNestedScrollView : NestedScrollView{

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val childAt = this@MNestedScrollView.getChildAt(childCount - 1)
        if (childAt is RecyclerView){
            val params= childAt.layoutParams as LinearLayout.LayoutParams
            params.height=measuredHeight
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}