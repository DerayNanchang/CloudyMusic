package com.lsn.module.music.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.module.music.R
import com.lsn.module.music.databinding.FragmentMusicHomeBinding
import com.lsn.module.music.ui.viewmodel.MusicViewModel
import com.lsn.module.provider.comm.constant.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.MUSIC.INDEX)
class MusicHomeFragment :
    BaseCoreFragment<MusicViewModel, FragmentMusicHomeBinding>(R.layout.fragment_music_home) {

}