package com.pmisy.roomkb.ui.activity

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.utils.WeakCacheUtil
import com.lsn.lib.base.ConventionalListener
import com.lsn.lib.net.core.utils.CacheUtil
import com.lsn.lib.ui.widget.TypeTextView
import com.lsn.module.entrance.R
import com.lsn.module.entrance.databinding.ActivityWelcomeBinding
import com.lsn.module.entrance.manager.SPManager
import com.pmisy.roomkb.ui.viewmodel.WelcomeViewModel
import kotlinx.coroutines.delay


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
class WelcomeActivity :
    BaseCoreActivity<WelcomeViewModel, ActivityWelcomeBinding>(R.layout.activity_welcome),
    TypeTextView.OnTypeViewListener {

    private val mTypes: Array<String> by lazy {
        resources.getStringArray(R.array.nonsenses)
    }

    override fun initView() {
        super.initView()


        val tips = mTypes[(mTypes.indices).random()]
        binding.tvType.run {
            if (WeakCacheUtil.isOpenLauncherText()) {
                setOnTypeViewListener(this@WelcomeActivity)
                start(tips, 120)
            } else {
                text = tips
                startToMain(2000)
            }
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun onTypeStart() {

    }

    override fun onTypeOver() {
        startToMain(800)
    }


    private fun startToMain(time: Long) {
        delay(time){
            /*var intent = QMUILatestVisit.intentOfLatestVisit(this)
            if (intent == null) {
                intent = Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()*/
            startActivity<Main>()
        }
    }

}