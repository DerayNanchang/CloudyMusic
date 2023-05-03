package com.lsn.comm.core.entity

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.lsn.comm.core.R


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 下午 05:00
 * @Description :
 *
 *
 */

data class CoilDataEntity(
    var isCrossfade: Boolean = false,
    var isPlaceholder: Boolean = false,
    var isError: Boolean = false,
    var radius: Float = 10f,
    var topStart: Float = 10f,
    var topEnd: Float = 10f,
    var bottomStart: Float = 10f,
    var bottomEnd: Float = 10f,
    var blurRadius: Float = 5f,
    var blurSampling: Float = 10f,
    var placeholderRes: Int = com.lsn.lib.base.R.mipmap.ic_launcher, //@DrawableRes
//    var placeholderDra:Drawable = com.lsn.lib.ui.R.drawable.ic_svg_back_b
    var errorRes: Int = com.lsn.lib.base.R.mipmap.ic_launcher,//@DrawableRes
//    var errorDra:Drawable = com.lsn.lib.base.R.mipmap.ic_launcher,
)

