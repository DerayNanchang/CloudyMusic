package com.lsn.module.music.ui.activity

import android.os.Bundle
import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ActivityMeReadyBinding
import com.lsn.module.music.ui.viewmodel.MeReadyViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 下午 06:43
 * @Description :
 */
class MeReadyActivity :
    BaseCoreActivity<MeReadyViewModel, ActivityMeReadyBinding>(R.layout.activity_me_ready) {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }


    override fun onSuccess(it: ResponseEntity) {
        super.onSuccess(it)



    }



}