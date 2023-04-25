package com.lsn.module.settings.provider

import android.content.Context
import com.lsn.module.provider.main.provide.SettingsProvider
import com.lsn.module.provider.scheduler.RouterHelp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
class SettingsProvideImpl : SettingsProvider {
    override fun actionSettingsIndex() {
        RouterHelp.get.actionSettingsIndex()
    }

    override fun init(context: Context?) {

    }
}