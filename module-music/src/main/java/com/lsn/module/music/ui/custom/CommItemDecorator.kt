package com.lsn.module.music.ui.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 下午 02:51
 * @Description :
 */
class CommItemDecorator(count: Int) : RecyclerView.ItemDecoration() {

    private val spanCount = count
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        fun dp2px(dp: Int): Int {
            val scale = view.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        val itemWidth = parent.width / spanCount  //item外宽度
        val itemWidthInside = dp2px(65) //item内宽度
        val padding = itemWidth - itemWidthInside // p
        val space = (parent.width - spanCount * itemWidthInside) / 3 // space
        if (column == 0) {
            outRect.left = 0
            outRect.right = padding
        } else if (column == 1) {
            outRect.left = space - (padding)
            outRect.right = padding * 2 - space
        } else if (column == 2) {
            outRect.left = space * 2 - padding * 2
            outRect.right = padding * 3 - space * 2
        } else if (column == 3) {
            outRect.left = padding
            outRect.right = 0
        }

    }


}