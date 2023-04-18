package androidx.recyclerview.widget


/**
 * @Author : lsn
 * @CreateTime : 2023/3/22 上午 10:00
 * @Description :
 */
object RecyclerViewUtils {

    fun setItemViewType(vh: RecyclerView.ViewHolder, viewType: Int) {
        vh.mItemViewType = viewType
    }
}