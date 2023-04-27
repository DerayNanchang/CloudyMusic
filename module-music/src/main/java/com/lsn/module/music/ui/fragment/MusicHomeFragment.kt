package com.lsn.module.music.ui.fragment

import android.os.Bundle
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.module.music.R
import com.lsn.module.music.databinding.FragmentMusicHomeBinding
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


    override fun getViewModelClass(): Class<MusicHomeViewModel> {
        return MusicHomeViewModel::class.java
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun initData() {
        super.initData()
        viewModel.getBanner()
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
        }
    }

}