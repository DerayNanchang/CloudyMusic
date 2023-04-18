package com.pmisy.roomkb.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 下午 02:57
 * @Description :
 */
open class BaseBindViewHolder<BD : ViewDataBinding>(val dataBinding: BD) : RecyclerView.ViewHolder(dataBinding.root)