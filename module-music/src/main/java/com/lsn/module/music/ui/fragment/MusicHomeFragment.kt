package com.lsn.module.music.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.lib.ui.widget.layout.linkage.view.LinkageLinearLayout
import com.lsn.module.music.R
import com.lsn.module.music.adapter.AlbumNewAdapter
import com.lsn.module.music.databinding.FragmentMusicHomeBinding
import com.lsn.module.music.entity.HomeSimpleItemData
import com.lsn.module.music.entity.MusicAlbum
import com.lsn.module.music.entity.MusicBannerList
import com.lsn.module.music.ui.viewmodel.MusicHomeViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import com.lsn.module.provider.comm.constant.Constants
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.MUSIC.INDEX)
@AndroidEntryPoint
class MusicHomeFragment :
    BaseCoreFragment<MusicHomeViewModel, FragmentMusicHomeBinding>(R.layout.fragment_music_home) {


    val albumNewAdapter by lazy { AlbumNewAdapter() }
    val albumNewestAdapter by lazy { AlbumNewAdapter() }
    val mvAdapter by lazy { AlbumNewAdapter() }
    val artistsAdapter by lazy { AlbumNewAdapter() }


    override fun getViewModelClass(): Class<MusicHomeViewModel> {
        return MusicHomeViewModel::class.java
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        binding.rvAlbumNew.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = albumNewAdapter
        }
        binding.rvAlbumNewest.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = albumNewestAdapter
        }
        binding.rvMv.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = mvAdapter
        }
        binding.rvArtists.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = artistsAdapter
        }

    }

    override fun initData() {
        super.initData()
        viewModel.getBanner()
        viewModel.getAlbumNew()
        viewModel.getAlbumNewest()
        viewModel.getMV()
        viewModel.getArtists()
    }

    override fun initEvent() {
        super.initEvent()
    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {
            ApiConstants.Music.BANNER -> {
                val data = it.data as List<BannerItem>
                binding.ribSimpleUsage.setSource(data).startScroll()
            }

            ApiConstants.Music.ALBUM_NEW -> {
                val data = it.data as MutableList<HomeSimpleItemData>
                albumNewAdapter.setData(data)
            }

            ApiConstants.Music.ALBUM_NEWEST -> {
                val data = it.data as MutableList<HomeSimpleItemData>
                albumNewestAdapter.setData(data)
            }
            ApiConstants.Music.MV_FIRST -> {
                val data = it.data as MutableList<HomeSimpleItemData>
                mvAdapter.setData(data)
            }
            ApiConstants.Music.TOP_ARTISTS -> {
                val data = it.data as MutableList<HomeSimpleItemData>
                artistsAdapter.setData(data)
            }
        }
    }

}