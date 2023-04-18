package com.lsn.lib.ui.widget.switchs.comm

import android.content.Context
import android.util.TypedValue

object Extensions {

    fun lerp(a: Float, b: Float, t: Float): Float =
        a + (b - a) * t

    fun Context.toPx(value: Float): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()

    fun isLollipopOrAbove(): Boolean =
        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
}