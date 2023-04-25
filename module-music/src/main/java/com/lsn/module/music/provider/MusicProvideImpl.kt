package com.lsn.module.music.provider

import android.content.Context
import com.lsn.module.provider.main.provide.MusicProvider
import com.lsn.module.provider.main.provide.SettingsProvider
import com.lsn.module.provider.scheduler.RouterHelp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
class MusicProvideImpl : MusicProvider {
    override fun actionMusicIndex() {
        RouterHelp.get.actionMusicIndex()
    }

    override fun init(context: Context?) {

    }
}