package com.lsn.module.settings.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.module.settings.R
import com.lsn.module.settings.databinding.FragmentSettingsHomeBinding
import com.lsn.module.settings.ui.viewmodel.SettingsViewModel
import com.pmisy.roomkb.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.GRAPHIC.VIDEO_INDEX)
class SettingsHomeFragment :
    BaseCoreFragment<SettingsViewModel, FragmentSettingsHomeBinding>(R.layout.fragment_settings_home) {

}