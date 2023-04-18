package com.lsn.comm.core.databinding

import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.lsn.comm.core.R


/**
 * @Author : lsn
 * @CreateTime : 2023/4/7 下午 01:58
 * @Description :
 */
@BindingMethods(
    BindingMethod(type = View::class, attribute = "android:enabled", method = "enabled"),
    BindingMethod(type = View::class, attribute = "android:selected", method = "selected"),
    BindingMethod(type = View::class, attribute = "android:activated", method = "activated"),
)
object AnimationBindingComponent {


    @BindingAdapter("doAnima")
    @JvmStatic
    fun doAnima(view: View, scale: Float) {
        if (scale == null) {
            return
        }

        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showOnFocusAnimation(view, scale)
            } else {
                showOnFocusAnimation(view, 1f)
            }
        }
    }


    private fun showOnFocusAnimation(v: View, scale: Float) {
        ViewCompat.animate(v)
            .scaleX(scale)
            .scaleY(scale)
            .translationZ(scale)
            .start()
    }
}