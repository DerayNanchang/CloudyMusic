package com.lsn.module.music.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.module.music.ui.fragment.MusicHomeFragment
import com.lsn.comm.core.constant.Constants
import com.lsn.module.provider.music.provide.MusicProvider
import com.lsn.module.provider.scheduler.RouterHelp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
@Route(path = Constants.RouterPath.MUSIC.PROVIDE)
class MusicProvideImpl : MusicProvider {
    override fun actionMusicIndex() {
        RouterHelp.get.actionMusicIndex()
    }

    override fun getMusicHomeFragment(): Fragment {
        return MusicHomeFragment()
    }

    override fun init(context: Context?) {

    }
}