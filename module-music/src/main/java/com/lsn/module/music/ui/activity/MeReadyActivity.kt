package com.lsn.module.music.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.PARA
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.module.music.R
import com.lsn.module.music.adapter.MeReadyAdapter
import com.lsn.module.music.databinding.ActivityMeReadyBinding
import com.lsn.module.music.entity.Playlist
import com.lsn.module.music.entity.PlaylistTitle
import com.lsn.module.music.ui.viewmodel.MeReadyViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 下午 06:43
 * @Description :
 */
@Toolbar("我的")
@AndroidEntryPoint
class MeReadyActivity :
    BaseCoreActivity<MeReadyViewModel, ActivityMeReadyBinding>(R.layout.activity_me_ready) {

    private var mAdapter: MeReadyAdapter? = null


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()

        viewModel.getPlaylistDetail(366231393)
    }

    override fun initEvent() {
        super.initEvent()





        binding.elView.setOnChildClickListener { _, _, groupPosition, childPosition, id ->

            startActivity<PlaylistDetailActivity>(
                PARA.LONG_ID to viewModel.playlistContentData.get()!![groupPosition][childPosition].id
            )
            true
        }

        mAdapter?.setOnContentListener(object : MeReadyAdapter.ContentListener {
            override fun onClick(data: Playlist, groupPosition: Int, childPosition: Int) {
                showToast("点击事件 : $childPosition")
                // 跳转到详情界面

            }
        })

    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {
            ApiConstants.Music.USER_PLAYLIST -> {
//                var playlists = it.data as MutableList<Playlist>
                mAdapter = MeReadyAdapter(
                    viewModel.playlistTitleData.get()!!,
                    viewModel.playlistContentData.get()!!
                )
                binding.elView.setAdapter(mAdapter!!)
            }
        }
    }
}