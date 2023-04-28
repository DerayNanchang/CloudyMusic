package com.lsn.module.music.ui.activity

import android.os.Bundle
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.annotation.StatusBar
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ActivityMusicListBinding
import com.lsn.module.music.ui.viewmodel.MusicListViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 下午 03:29
 * @Description :
 */
@StatusBar(isShow = false)
class MusicListActivity :
    BaseCoreActivity<MusicListViewModel, ActivityMusicListBinding>(R.layout.activity_music_list) {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }


    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }

}