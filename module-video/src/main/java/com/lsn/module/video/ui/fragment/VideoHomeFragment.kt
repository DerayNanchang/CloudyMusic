package com.lsn.module.video.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.module.video.R
import com.lsn.module.video.databinding.FragmentVideoHomeBinding
import com.lsn.module.video.ui.viewmodel.VideoViewModel
import com.lsn.comm.core.constant.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.VIDEO.INDEX)
class VideoHomeFragment :
    BaseCoreFragment<VideoViewModel, FragmentVideoHomeBinding>(R.layout.fragment_video_home) {

}