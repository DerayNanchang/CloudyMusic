package com.lsn.module.music.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 上午 10:52
 * @Description :
 */


object MusicAdapter {


    @BindingAdapter("xUrl", "xRes", requireAll = false)
    @JvmStatic
    fun setImageView(imageView: ImageView, url: String?, res: Any?) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)

        } else if (res != null) {
            Glide.with(imageView.context)
                .load(res)
                .into(imageView)
        }
    }

}