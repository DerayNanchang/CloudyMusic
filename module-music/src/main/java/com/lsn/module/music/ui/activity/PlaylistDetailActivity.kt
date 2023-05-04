package com.lsn.module.music.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnScrollChangeListener
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.utils.CoilUtil
import com.lsn.comm.core.utils.CoilUtil.getImageBitmapByUrl
import com.lsn.lib.base.PARA
import com.lsn.lib.base.annotation.StatusBar
import com.lsn.lib.utils.util.SPUtils
import com.lsn.lib.utils.util.SizeUtils
import com.lsn.module.music.R
import com.lsn.module.music.adapter.PlaylistDetailAdapter
import com.lsn.module.music.databinding.ActivityPlaylistDetailBinding
import com.lsn.module.music.entity.MusicPlaylistCurtRoot
import com.lsn.module.music.entity.TracksCurt
import com.lsn.module.music.ui.viewmodel.PlaylistDetailViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs


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
    private var pid: Long = 0L
    private var darkMutedColor: Int = -10

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        pid = intent.getLongExtra(PARA.LONG_ID, 0L)


        mAdapter = PlaylistDetailAdapter()
        binding.rvPlaylist.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }


    }

    override fun initData() {
        super.initData()
        if (pid != 0L) {
            viewModel.getPlaylistDetail(pid)
        }

    }

    override fun initEvent() {
        super.initEvent()

        val maxHeight = SizeUtils.dp2px(220f)
        binding.ablView.addOnOffsetChangedListener { appBarLayout, verticalOffset -> // 0 - 660

            if (maxHeight != 0) {
                val i = abs(verticalOffset) * 1f / maxHeight / 2
                if (i >= 0.4) {
                    viewModel.playlistCurtData.get()?.let {
                        binding.tvToolbarName.text = it?.title
                    }
                    if (i >= 0.495) {
                        if (darkMutedColor != -10) {
                            binding.vFiller.setBackgroundColor(darkMutedColor)
                        }
                    } else {
                        if (binding.vFiller.background != null) {
                            val colorDrawable = binding.vFiller.background as ColorDrawable
                            if (colorDrawable.color != Color.TRANSPARENT) {
                                binding.vFiller.setBackgroundColor(Color.TRANSPARENT)
                            }
                        }
                    }
                } else {
                    binding.tvToolbarName.text = "歌单"
                }
                binding.vColor.alpha = i
            }
        }
    }

    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {
            ApiConstants.Music.PLAYLIST_DETAIL -> {
                val musicPlaylistCurtRoot = it.data as MusicPlaylistCurtRoot
                val tracksCurts = musicPlaylistCurtRoot.tracksCurts


                CoilUtil.loadBlur(binding.ivBackground, musicPlaylistCurtRoot.coverImgUrl) {
                    blurRadius = 10f
                    blurSampling = 20f
                }
                CoilUtil.loadRounded(binding.ivCover, musicPlaylistCurtRoot.coverImgUrl)


                lifecycleScope.launch {
                    val imageBitmapByUrl = getImageBitmapByUrl(musicPlaylistCurtRoot.coverImgUrl)
                    imageBitmapByUrl?.let {
                        Palette.from(it).generate {
                            it?.apply {
//                                getDominantColor：获取图片中的主色调
//                                getMutedColor：获取图片中柔和的颜色
//                                getDarkMutedColor：获取图片中柔和的暗色
//                                getLightMutedColor：获取图片中柔和的亮色
//                                getVibrantColor：获取图片中有活力的颜色
//                                getDarkVibrantColor：获取图片中有活力的暗色
//                                getLightVibrantColor：获取图片中有活力的亮色
                                darkMutedColor =  getDarkVibrantColor(Color.TRANSPARENT)
                                if (darkMutedColor == Color.TRANSPARENT){
                                    darkMutedColor = getLightMutedColor(Color.TRANSPARENT)
                                }
                                binding.vColor.setBackgroundColor(darkMutedColor)
                            }
                        }
                    }
                }

                println("数据大小 : " + tracksCurts.size)
                mAdapter.setData(tracksCurts as MutableList<TracksCurt>)
            }
        }


    }
}