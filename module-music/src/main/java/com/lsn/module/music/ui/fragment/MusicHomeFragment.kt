package com.lsn.module.music.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.module.music.R
import com.lsn.module.music.adapter.MusicHomeSimpleItemAdapter
import com.lsn.module.music.adapter.MusicPersonalizedAdapter
import com.lsn.module.music.databinding.FragmentMusicHomeBinding
import com.lsn.module.music.entity.HomeSimpleItemData
import com.lsn.module.music.entity.MusicPersonalized
import com.lsn.module.music.ui.activity.MeReadyActivity
import com.lsn.module.music.ui.activity.PlaylistDetailActivity
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


    private val personalizedAdapter by lazy { MusicPersonalizedAdapter() }
    private val albumNewAdapter by lazy { MusicHomeSimpleItemAdapter() }
    private val albumNewestAdapter by lazy { MusicHomeSimpleItemAdapter() }
    private val mvAdapter by lazy { MusicHomeSimpleItemAdapter() }


    override fun getViewModelClass(): Class<MusicHomeViewModel> {
        return MusicHomeViewModel::class.java
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        initTitleAnni()
        setSwipeRefreshLayout(binding.srlView)
        binding.rvAlbumNew.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = albumNewAdapter
        }
        binding.tvPersonalizedlist.apply {
            val gridLayoutManager = GridLayoutManager(context, 3)
            layoutManager = gridLayoutManager
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            adapter = personalizedAdapter
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
    }

    override fun initData() {
        super.initData()
        request()
    }

    override fun initEvent() {
        super.initEvent()

        binding.llRecommend.setOnClickListener {
            startActivity<PlaylistDetailActivity>()
        }

        binding.llMeReady.setOnClickListener {
            startActivity<MeReadyActivity>()
        }


        binding.srlView.setOnRefreshListener {
            request()
        }


        binding.tvAlbumNew.setOnClickListener {
            binding.rvAlbumNew.visibility = View.VISIBLE
            binding.rvAlbumNewest.visibility = View.GONE
            initTitleAnni()
            binding.tvOrderName.text = "专辑广场"
        }


        binding.tvAlbumNewest.setOnClickListener {
            binding.rvAlbumNew.visibility = View.GONE
            binding.rvAlbumNewest.visibility = View.VISIBLE
            binding.tvAlbumNew.setTextColor(resources.getColor(com.lsn.lib.ui.R.color.c999999))
            binding.tvAlbumNew.textSize = 12f
            binding.tvAlbumNewest.setTextColor(resources.getColor(com.lsn.lib.ui.R.color.black))
            binding.tvAlbumNewest.textSize = 14f

            binding.tvAlbumNew.animate().scaleX(1f).scaleY(1f)
            binding.tvAlbumNewest.animate().scaleX(1.1f).scaleY(1.1f)
            binding.tvOrderName.text = "新碟广场"
        }

    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {
            ApiConstants.Music.BANNER -> {
                val data = it.data as List<BannerItem>
                binding.ribSimpleUsage.setSource(data).startScroll()
            }

            ApiConstants.Music.PERSONALIZED -> {
                val data = it.data as MutableList<MusicPersonalized>
                personalizedAdapter.setData(data)
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
        }
    }


    private fun initTitleAnni() {
        binding.tvAlbumNew.setTextColor(resources.getColor(com.lsn.lib.ui.R.color.black))
        binding.tvAlbumNew.textSize = 14f
        binding.tvAlbumNewest.setTextColor(resources.getColor(com.lsn.lib.ui.R.color.c999999))
        binding.tvAlbumNewest.textSize = 12f
        binding.tvAlbumNew.animate().scaleX(1.1f).scaleY(1.1f)
        binding.tvAlbumNewest.animate().scaleX(1f).scaleY(1f)
    }


    private fun request() {
        viewModel.getBanner()
        viewModel.getPersonalized(6)
        viewModel.getAlbumNew()
        viewModel.getAlbumNewest()
        viewModel.getMV()
    }

}