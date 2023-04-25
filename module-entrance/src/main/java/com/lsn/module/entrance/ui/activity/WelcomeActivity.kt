package com.lsn.module.entrance.ui.activity

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.utils.WeakCacheUtil
import com.lsn.lib.ui.widget.TypeTextView
import com.lsn.module.entrance.R
import com.lsn.module.provider.comm.api.ApiConstants
import com.lsn.module.entrance.databinding.ActivityWelcomeBinding
import com.lsn.module.entrance.entity.HPImageArchiveEntity
import com.lsn.module.entrance.ui.viewmodel.WelcomeViewModel
import com.lsn.module.provider.main.provide.MainProvider
import com.lsn.module.provider.scheduler.RouterHelp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.start


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */
@AndroidEntryPoint
class WelcomeActivity :
    BaseCoreActivity<WelcomeViewModel, ActivityWelcomeBinding>(R.layout.activity_welcome),
    TypeTextView.OnTypeViewListener {


    @JvmField
    @Autowired(name = RouterHelp.MAIN_PROVIDE)
    var mainProvider: MainProvider? = null


    override fun getViewModelClass(): Class<WelcomeViewModel> {
        return WelcomeViewModel::class.java
    }

    override fun initView() {
        super.initView()
        hideStatusBar(this)
    }

    override fun initData() {
        super.initData()

        viewModel.getHPImageArchive()

    }

    override fun onTypeStart() {


    }

    override fun onTypeOver() {
        startToMain(800)
    }


    override fun onResponseReceiver() {
        super.onResponseReceiver()

        viewModel.success.observe(this) {

            when (it.api) {
                ApiConstants.Entrance.HITOKOTO_ENCODE -> {
                    /*val hitokotoEncodeEntity = it.data as HitokotoEncodeEntity
                    val tips = hitokotoEncodeEntity.hitokoto
                    binding.tvType.run {
                        if (WeakCacheUtil.isOpenLauncherText()) {
                            setOnTypeViewListener(this@WelcomeActivity)
                            start(tips, 120)
                        } else {
                            text = tips
                            startToMain(2000)
                        }
                    }*/
                }


                ApiConstants.Entrance.HP_IMAGE_ARCHIVE -> {
                    val hitokotoEncodeEntity = it.data as HPImageArchiveEntity
                    val tips = hitokotoEncodeEntity.images[0].desc
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
            }

        }

    }

    fun hideStatusBar(activity: Activity?) {
        if (activity == null) return
        val window: Window = activity.getWindow() ?: return
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        val lp: WindowManager.LayoutParams = window.getAttributes()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        window.setAttributes(lp)
    }

    private fun startToMain(time: Long) {
        Handler().postDelayed({
            mainProvider?.actionMain()
            finish()
        }, time)
    }


}