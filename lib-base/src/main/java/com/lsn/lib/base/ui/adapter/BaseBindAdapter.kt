package com.lsn.lib.base.ui.adapter

import android.annotation.SuppressLint
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 下午 02:54
 * @Description :
 */
abstract class BaseBindAdapter<DATA : Any, VB : ViewDataBinding>(@LayoutRes var resId: Int) :
    RecyclerView.Adapter<BaseBindViewHolder<VB>>() {


    private var dataList: MutableList<DATA> = ArrayList()
    protected lateinit var binding: VB
    private var mLastPosition = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<DATA>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    fun addData(data: DATA) {
        dataList.add(data)
    }


    override fun getItemCount() = dataList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindViewHolder<VB> {

        binding = DataBindingUtil.bind(
            LayoutInflater.from(parent.context).inflate(resId, parent, false)
        )!!

        return BaseBindViewHolder(binding)
    }

    protected open fun SparseArray<Any>.bindingParams() {}

    override fun onBindViewHolder(holder: BaseBindViewHolder<VB>, position: Int) {
        holder.itemView.setOnClickListener {
            if (isLoadingScrollAlpha()) {
                addAnimate(holder, holder.layoutPosition)
            }
            itemClick?.onItemClick(position, dataList[position])
        }


        holder.dataBinding.run {
            this.setVariable(BR.item, dataList[position])
            this.setVariable(BR.position, position)
            SparseArray<Any>().apply {
                bindingParams()
                forEach { key, any ->
                    setVariable(key, any)
                }
            }.clear()
        }

        onExtBindingDataHolder(dataList[position], position)
    }

    protected open fun onExtBindingDataHolder(data: DATA, position: Int) {

    }

    var itemClick: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pposition: Int, data: Any)
    }


    open fun setOnItemClickListener(itemClick: OnItemClickListener) {
        this.itemClick = itemClick
    }


    protected fun isLoadingScrollAlpha() : Boolean{
        return true
    }


    private fun addAnimate(holder: BaseBindViewHolder<VB>, position: Int) {
        if (mLastPosition < position) {
            holder.itemView.alpha = 0f
            holder.itemView.animate().alpha(1f).start()
            mLastPosition = position
        }
    }
}