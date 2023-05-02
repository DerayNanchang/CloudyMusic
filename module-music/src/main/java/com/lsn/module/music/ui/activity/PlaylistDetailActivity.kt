package com.lsn.module.music.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.annotation.StatusBar
import com.lsn.module.music.R
import com.lsn.module.music.adapter.PlaylistDetailAdapter
import com.lsn.module.music.databinding.ActivityPlaylistDetailBinding
import com.lsn.module.music.entity.MusicPlaylistCurtRoot
import com.lsn.module.music.entity.TracksCurt
import com.lsn.module.music.ui.viewmodel.PlaylistDetailViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 下午 03:29
 * @Description :
 */
@StatusBar(isShow = false)
@AndroidEntryPoint
class PlaylistDetailActivity :
    BaseCoreActivity<PlaylistDetailViewModel, ActivityPlaylistDetailBinding>(R.layout.activity_playlist_detail) {

    private lateinit var mAdapter: PlaylistDetailAdapter

    override fun getViewModelClass(): Class<PlaylistDetailViewModel> {
        return PlaylistDetailViewModel::class.java
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        mAdapter = PlaylistDetailAdapter()

        binding.rvPlaylist.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    override fun initData() {
        super.initData()
        viewModel.getPlaylistDetail(516736946)
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {
            ApiConstants.Music.PLAYLIST_DETAIL -> {
                val musicPlaylistCurtRoot = it.data as MusicPlaylistCurtRoot
                val tracksCurts = musicPlaylistCurtRoot.tracksCurts
                mAdapter.setData(tracksCurts as MutableList<TracksCurt>)
            }
        }


    }
}