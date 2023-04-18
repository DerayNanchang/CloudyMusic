package com.lsn.comm.core.databinding

import android.view.View
import java.util.concurrent.TimeUnit

/**
 * @Author : lsn
 * @CreateTime : 2023/3/29 上午 10:25
 * @Description :
 */
fun View.throttleClick(
    interval: Long = 500,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    block: View.() -> Unit
) {
    setOnClickListener(ThrottleClickListener(interval, unit, block))
}

class ThrottleClickListener(
    private val interval: Long = 500,
    private val unit: TimeUnit = TimeUnit.MILLISECONDS,
    private var block: View.() -> Unit
) : View.OnClickListener {
    private var lastTime: Long = 0

    override fun onClick(v: View) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime > unit.toMillis(interval)) {
            lastTime = currentTime
            block(v)
        }
    }
}