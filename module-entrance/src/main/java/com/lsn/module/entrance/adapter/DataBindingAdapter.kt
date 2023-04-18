package com.pmisy.roomkb.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.pmisy.roomkb.app.PMIJEKanbanApplication

object DataBindingAdapter {




    @BindingAdapter("ratioColor")
    @JvmStatic
    fun setRatioColor(textView: TextView, ratio: Double) {
        if (ratio < 90) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.red))
        } else if (ratio in 90.0..95.9) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.yellow))
        } else if (ratio > 96.0) {
            textView.setTextColor(textView.resources.getColor(com.lsn.lib.ui.R.color.green))
        }
        textView.text = format2(ratio) + "%"
    }


    @BindingAdapter("statusInc")
    @JvmStatic
    fun setStatusInc(textView: TextView, statusId: String) {
        if (!TextUtils.isEmpty(PMIJEKanbanApplication.application.eqStatusDescHashMap[statusId])) {
            textView.text = PMIJEKanbanApplication.application.eqStatusDescHashMap[statusId]
        }

        if (!TextUtils.isEmpty(PMIJEKanbanApplication.application.eqStatusColorHashMap[statusId])) {
            textView.setTextColor(Color.parseColor(PMIJEKanbanApplication.application.eqStatusColorHashMap[statusId]))
        }
    }


    fun format2(value: Double): String {
        return String.format("%.2f", value)
    }

}