package com.lsn.module.video.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.constant.Constants
import com.lsn.module.provider.scheduler.RouterHelp
import com.lsn.module.provider.video.provide.VideoProvider
import com.lsn.module.video.ui.fragment.VideoHomeFragment


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
@Route(path = Constants.RouterPath.VIDEO.PROVIDE)
class VideoProvideImpl : VideoProvider {
    override fun actionVideoIndex() {

        RouterHelp.get.actionVideoIndex()
    }

    override fun getVideoHomeFragment(): Fragment {
        return VideoHomeFragment()
    }

    override fun init(context: Context?) {

    }
}