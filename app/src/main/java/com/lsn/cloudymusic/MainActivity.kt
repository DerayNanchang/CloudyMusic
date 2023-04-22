package com.lsn.cloudymusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.cloudy.R
import com.lsn.module.provider.scheduler.RouterHelp

@Route(path = RouterHelp.MAIN_INDEX)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_main)
    }
}