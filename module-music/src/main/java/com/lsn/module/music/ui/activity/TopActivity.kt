package com.lsn.module.music.ui.activity

import android.os.Bundle
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.ui.widget.rv.BindingAdapter
import com.lsn.lib.ui.widget.rv.utils.linear
import com.lsn.lib.ui.widget.rv.utils.setup
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ActivityTopBinding
import com.lsn.module.music.entity.MusicTopCurtData
import com.lsn.module.music.ui.viewmodel.TopViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import com.umeng.analytics.pro.t
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/5/6 上午 10:06
 * @Description :
 */
@AndroidEntryPoint
class TopActivity : BaseCoreActivity<TopViewModel, ActivityTopBinding>(R.layout.activity_top) {


    private lateinit var mAdapter: BindingAdapter


    companion object {

        const val VIEW_TYPE_TITLE = -1
        const val VIEW_TYPE_CONTENT_HOT = 0
        const val VIEW_TYPE_CONTENT_STANDARD = 1

    }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        mAdapter = binding.rvContent.linear().setup {
            addType<MusicTopCurtData> {
                when (type) {
                    VIEW_TYPE_TITLE -> R.layout.item_top_title
                    VIEW_TYPE_CONTENT_HOT -> R.layout.item_top_content_hot
                    else -> R.layout.item_top_content_standard
                }
            }
        }

    }


    override fun initData() {
        super.initData()
    }


    override fun initEvent() {
        super.initEvent()
    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)
        when (it.api) {
            ApiConstants.Music.TOPLIST_DETAIL -> {
                val musicTopCurtData = it.data as MutableList<MusicTopCurtData>


            }
        }

    }

}