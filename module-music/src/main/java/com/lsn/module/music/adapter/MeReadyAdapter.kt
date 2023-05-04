package com.lsn.module.music.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lsn.comm.core.utils.CoilUtil
import com.lsn.module.music.R
import com.lsn.module.music.entity.Playlist
import com.lsn.module.music.entity.PlaylistTitle

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/4/25
 * Description
 */
class MeReadyAdapter(
    var groups: ArrayList<PlaylistTitle>,
    var contents: ArrayList<ArrayList<Playlist>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount() = groups.size

    override fun getChildrenCount(groupPosition: Int) = contents[groupPosition].size

    override fun getGroup(groupPosition: Int): Any {
        return groups[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return contents[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong();
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds() = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var view = convertView;
        var groupViewHolder: GroupViewHolder?
        if (view == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_me_ready_title, parent, false);
            groupViewHolder = GroupViewHolder()
            groupViewHolder.llMeReadyRoot = view.findViewById(R.id.llMeReadyRoot)
            groupViewHolder.ivIcon = view.findViewById(R.id.ivIcon)
            groupViewHolder.tvTitle = view.findViewById(R.id.tvTitle)
            groupViewHolder.tvDesc = view.findViewById(R.id.tvDesc)
            view.tag = groupViewHolder
        } else {
            groupViewHolder = view.tag as GroupViewHolder
        }
        groupViewHolder.tvTitle.text = groups[groupPosition].title
        groupViewHolder.tvDesc.text = "(" + groups[groupPosition].desc + ")"

        if (isExpanded) {
            groupViewHolder.ivIcon.animate().rotation(90f)
        } else {
            groupViewHolder.ivIcon.animate().rotation(0f)
        }
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {

        var view = convertView;
        var childView: ChildViewHolder?
        if (view == null) {
            view =
                LayoutInflater.from(parent?.context).inflate(R.layout.item_me_ready, parent, false);
            childView = ChildViewHolder()
            childView.clMeReady = view.findViewById(R.id.clMeReady)
            childView.ivCover = view.findViewById(R.id.ivCover)
            childView.tvName = view.findViewById(R.id.tvName)
            childView.tvCount = view.findViewById(R.id.tvCount)
            childView.tvContent = view.findViewById(R.id.tvContent)
            view.tag = childView
        } else {
            childView = view.tag as ChildViewHolder
        }

        childView.apply {
            tvName.text = contents[groupPosition][childPosition].name
            tvCount.text = contents[groupPosition][childPosition].trackCount.toString() + "首"
            val desc = contents[groupPosition][childPosition].desc
            if (TextUtils.isEmpty(desc)){
                tvContent.text = "这个人很懒什么都没有留下"
            }else{
                tvContent.text = desc
            }
            CoilUtil.loadRounded(ivCover, contents[groupPosition][childPosition].picUrl) {
                isCrossfade = true
            }
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    private var titleListener: TitleListener? = null

    open interface TitleListener {
        fun onClick(data: PlaylistTitle, position: Int, isExpanded: Boolean)
    }

    fun setOnTitleListener(titleListener: TitleListener) {
        this.titleListener = titleListener
    }


    private var contentListener: ContentListener? = null

    interface ContentListener {
        fun onClick(data: Playlist, groupPosition: Int, childPosition: Int)
    }

    fun setOnContentListener(contentListener: ContentListener) {
        this.contentListener = contentListener
    }


    inner class GroupViewHolder {
        lateinit var llMeReadyRoot: LinearLayout
        lateinit var ivIcon: ImageView
        lateinit var tvTitle: TextView
        lateinit var tvDesc: TextView
    }

    inner class ChildViewHolder {
        lateinit var clMeReady: ConstraintLayout
        lateinit var ivCover: ImageView
        lateinit var tvName: TextView
        lateinit var tvCount: TextView
        lateinit var tvContent: TextView
    }
}