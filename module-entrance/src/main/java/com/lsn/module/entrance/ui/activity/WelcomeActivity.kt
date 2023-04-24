package com.lsn.module.entrance.ui.activity

import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.utils.WeakCacheUtil
import com.lsn.lib.base.bus.LiveBus
import com.lsn.lib.ui.widget.TypeTextView
import com.lsn.module.entrance.R
import com.lsn.module.entrance.databinding.ActivityWelcomeBinding
import com.lsn.module.provider.main.provide.MainProvider
import com.lsn.module.provider.scheduler.RouterHelp
import com.pmisy.roomkb.ui.viewmodel.WelcomeViewModel


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
class WelcomeActivity :
    BaseCoreActivity<WelcomeViewModel, ActivityWelcomeBinding>(R.layout.activity_welcome),
    TypeTextView.OnTypeViewListener {



    @JvmField
    @Autowired(name = RouterHelp.MAIN_PROVIDE)
    var mainProvider: MainProvider ? = null


    override fun getViewModelClass(): Class<WelcomeViewModel> {
        return WelcomeViewModel::class.java
    }

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
                LiveBus.get().with("",String::class.java).postValue("")
                mainProvider?.actionMain()
            } else {
                text = tips
                startToMain(2000)
                mainProvider?.actionMain()
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
//        startToMain(800)
    }


    private fun startToMain(time: Long) {

        Handler().postDelayed({

//            var mainProvider = ARouter.getInstance().build(RouterHelp.MAIN_PROVIDE) as MainProvider
//            mainProvider?.actionMain()
        }, time)
    }


}