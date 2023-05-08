package com.lsn.module.music.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lsn.lib.ui.widget.banner.recycler.BannerLayout
import com.lsn.lib.ui.widget.textview.ListTextView


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 上午 11:29
 * @Description :
 */
object TopAdapter {

    @BindingAdapter("lData")
    @JvmStatic
    fun blAdapter(ltv: ListTextView, data: List<String>?){
        data?.let {
            ltv.setData(data as ArrayList<String>)
        }
    }
}