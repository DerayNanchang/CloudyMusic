package com.lsn.lib.ui.widget.switchs

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow


/**
 * @Author : lsn
 * @CreateTime : 2023/4/18 下午 05:15
 * @Description :
 */
class BounceInterpolator(
    private val amplitude: Double,
    private val frequency: Double
) : Interpolator {
    override fun getInterpolation(time: Float): Float =
        (-1 * Math.E.pow(-time / amplitude) * cos(frequency * time) + 1).toFloat()
}