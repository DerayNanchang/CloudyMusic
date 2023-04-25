package com.lsn.cloudymusic.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.module.provider.main.provide.MainProvider
import com.lsn.module.provider.main.provide.VideoProvider
import com.lsn.module.provider.scheduler.RouterHelp
import com.pmisy.roomkb.Constants
import com.pmisy.roomkb.EntranceConstants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 08:32
 * @Description :
 */
@Route(path = Constants.RouterPath.MAIN.PROVIDE)
class MainProviderImpl : MainProvider {
    override fun actionMain() {
        RouterHelp.get.actionMain()
    }

    override fun init(context: Context?) {

    }

}
