package com.lsn.comm.core.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lsn.lib.ui.widget.banner.recycler.BannerLayout

object BindingAdapterComponent {

    @BindingAdapter("blAdapter")
    @JvmStatic
    fun blAdapter(bannerLayout: BannerLayout,adapter: RecyclerView.Adapter<*>){
        bannerLayout.setAdapter(adapter)
    }
}