package com.lsn.module.music.ui.activity

import android.os.Bundle
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.utils.WeakCacheUtil
import com.lsn.lib.base.PARA
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.module.music.R
import com.lsn.module.music.adapter.MeReadyAdapter
import com.lsn.module.music.databinding.ActivityMeReadyBinding
import com.lsn.module.music.entity.DecUserPlaylist
import com.lsn.module.music.entity.HitokotoEncodeEntity
import com.lsn.module.music.ui.viewmodel.MeReadyViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 下午 06:43
 * @Description :
 */
@Toolbar(isAdd = false)
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

        viewModel.getHitokotoEncode()
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
            override fun onClick(data: DecUserPlaylist, groupPosition: Int, childPosition: Int) {
                showToast("点击事件 : $childPosition")
                // 跳转到详情界面

            }
        })

    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)

        when (it.api) {

            ApiConstants.Music.HITOKOTO_ENCODE -> {
                val hitokotoEncodeEntity = it.data as HitokotoEncodeEntity
                val tips = hitokotoEncodeEntity.hitokoto
                binding.ttVDesc.run {
                    if (WeakCacheUtil.isOpenLauncherText()) {
                        start(tips, 120)
                    } else {
                        text = tips
                    }
                }
                binding.tvOwner.text = "from - " + hitokotoEncodeEntity.from_who
            }

            ApiConstants.Music.USER_PLAYLIST -> {
//                var playlists = it.data as MutableList<Playlist>
                mAdapter = MeReadyAdapter(
                    viewModel.playlistTitleData.get()!!,
                    viewModel.playlistContentData.get()!!
                )
                binding.elView.setAdapter(mAdapter!!)
                for (i in 0 until mAdapter!!.getGroupCount()) {
                    binding.elView.expandGroup(i)
                }
            }
        }
    }
}