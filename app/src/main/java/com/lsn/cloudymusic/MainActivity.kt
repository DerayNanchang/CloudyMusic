package com.lsn.cloudymusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.cloudy.R
import com.lsn.cloudy.databinding.ActivityMainBinding
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.module.provider.scheduler.RouterHelp
import com.umeng.analytics.pro.ac

@Route(path = RouterHelp.MAIN_INDEX)
@Toolbar(title = "这是首页", showBack = false)
class MainActivity : BaseCoreActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {


    override fun initView() {
        super.initView()


    }

}