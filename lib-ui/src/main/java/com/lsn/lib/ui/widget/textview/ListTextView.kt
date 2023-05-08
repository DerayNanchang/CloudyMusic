package com.lsn.lib.ui.widget.textview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lsn.lib.ui.R
import com.lsn.lib.utils.util.SizeUtils


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 上午 11:04
 * @Description :
 */
class ListTextView : LinearLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL

    }


    fun setData(data: ArrayList<String>) {
//        var i = 0
        data.forEach {
            val textView = TextView(context)
//            if (i == 0) {
//                textView.setBackgroundResource(R.color.green)
//            }
            textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f)
            textView.gravity = Gravity.CENTER_VERTICAL
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            textView.setTextColor(resources.getColor(R.color.c333333))
            textView.text = it
//            i++
            addView(textView)
        }
        invalidate()
    }
}