package com.lsn.module.music.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.lsn.comm.core.utils.CoilUtil
import com.lsn.lib.ui.widget.textview.ListTextView


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 上午 11:29
 * @Description :
 */
object MusicAdapter {

    @BindingAdapter("lData")
    @JvmStatic
    fun blAdapter(ltv: ListTextView, data: List<String>?){
        data?.let {
            ltv.setData(data as ArrayList<String>)
        }
    }


    @BindingAdapter("xERUrl", "xERRes", requireAll = false)
    @JvmStatic
    fun setErrorImageViewR(imageView: ImageView, url: String?, res: Any?) {
        if (!TextUtils.isEmpty(url)) {
            CoilUtil.loadRounded(imageView, url) {
                isCrossfade = true
                errorRes = com.lsn.module.music.R.mipmap.ic_def_al
            }
        }
    }

    @BindingAdapter("xEUrl", "xERes", requireAll = false)
    @JvmStatic
    fun setErrorImageView(imageView: ImageView, url: String?, res: Any?) {
        if (!TextUtils.isEmpty(url)) {
            CoilUtil.load(imageView, url) {
                isCrossfade = true
                errorRes = com.lsn.module.music.R.mipmap.ic_def_al
            }
        }
    }
}