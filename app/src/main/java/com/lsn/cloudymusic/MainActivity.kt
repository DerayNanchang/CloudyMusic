package com.lsn.cloudymusic

import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.cloudy.R
import com.lsn.cloudy.databinding.ActivityMainBinding
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.annotation.Toolbar
import com.pmisy.roomkb.EntranceConstants

@Route(path = EntranceConstants.RouterPath.MAIN.INDEX)
@Toolbar(title = "Welcome", showBack = false)
class MainActivity : BaseCoreActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {


    override fun initView() {
        super.initView()


    }

}