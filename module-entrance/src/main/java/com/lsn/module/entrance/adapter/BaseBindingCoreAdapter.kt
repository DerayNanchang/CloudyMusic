package com.pmisy.roomkb.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.pmisy.roomkb.R


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 上午 11:19
 * @Description :
 */
abstract class BaseBindingCoreAdapter<DATA : Any, VB : ViewDataBinding>(resId: Int) :
    BaseBindAdapter<DATA, VB>(resId) {


    fun setBGColor(view: View, position: Int) {
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.black)
        } else {
            view.setBackgroundResource(com.lsn.lib.ui.R.color.E4D5995)
        }
        view.alpha = 0.4f
    }


    fun setBGColor2(view: View, position: Int, textView: TextView) {
        if (position % 2 == 0) {
            view.setBackgroundResource(R.color.black)
            textView.setTextColor(view.resources.getColor(com.lsn.lib.ui.R.color.ff8d4f))
        } else {
            view.setBackgroundResource(com.lsn.lib.ui.R.color.E4D5995)
            textView.setTextColor(view.resources.getColor(com.lsn.lib.ui.R.color.X479dff))
        }
        view.alpha = 0.4f
    }


    fun setRatioColor3(ratio: Double, textView: TextView, isAdd: Boolean) {
        if (ratio < 90) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.red))
        } else if (ratio in 90.0..95.9) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.yellow))
        } else if (ratio > 96.0) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.green))
        }
        if (isAdd) {
            textView.text = format2(ratio) + "%"
        }
    }


    fun format2(value: Double): String {
        return String.format("%.2f", value)
    }

}