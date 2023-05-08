package com.lsn.module.music.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.PARA
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.lib.ui.widget.rv.BindingAdapter
import com.lsn.lib.ui.widget.rv.utils.grid
import com.lsn.lib.ui.widget.rv.utils.linear
import com.lsn.lib.ui.widget.rv.utils.setup
import com.lsn.lib.utils.util.DeviceUtils
import com.lsn.lib.utils.util.DeviceUtils.getModel
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ActivityTopBinding
import com.lsn.module.music.entity.MusicTopCurtData
import com.lsn.module.music.ui.custom.CommItemDecorator
import com.lsn.module.music.ui.viewmodel.TopViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import com.uc.crashsdk.export.LogType.addType
import com.umeng.analytics.pro.t
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/5/6 上午 10:06
 * @Description :
 */
@Toolbar("排行榜")
@AndroidEntryPoint
class TopActivity : BaseCoreActivity<TopViewModel, ActivityTopBinding>(R.layout.activity_top) {


    private lateinit var mHotAdapter: BindingAdapter
    private lateinit var mNetAdapter: BindingAdapter
    private lateinit var mRecommendAdapter: BindingAdapter


    companion object {

        const val VIEW_TYPE_TITLE = -1
        const val VIEW_TYPE_CONTENT_HOT = 0
        const val VIEW_TYPE_CONTENT_STANDARD = 1

    }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mHotAdapter = binding.rvHotContent.linear().setup {
            addType<MusicTopCurtData>(R.layout.item_top_content_hot)
            actionDetail()
        }
        mNetAdapter = binding.rvNetContent.grid(3).setup {
            addType<MusicTopCurtData>(R.layout.item_top_content_standard)
            actionDetail()
        }
        mRecommendAdapter = binding.rvRecommendContent.grid(3).setup {
            addType<MusicTopCurtData>(R.layout.item_top_content_standard)
            actionDetail()
        }
    }

    override fun initData() {
        super.initData()
        viewModel.getTop()
    }


    override fun initEvent() {
        super.initEvent()
    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)
        when (it.api) {
            ApiConstants.Music.TOPLIST_DETAIL -> {
                val musicTopCurtData = it.data as HashMap<String, List<MusicTopCurtData>>

                mHotAdapter.models = musicTopCurtData["官方榜"]
                mNetAdapter.models = musicTopCurtData["曲风榜"]
                mRecommendAdapter.models = musicTopCurtData["特色榜"]
            }
        }
    }

    private fun BindingAdapter.actionDetail() {
        R.id.clContent.onClick {
            val id = getModel<MusicTopCurtData>().id
            startActivity<PlaylistDetailActivity>(PARA.LONG_ID to id)
        }
    }

}