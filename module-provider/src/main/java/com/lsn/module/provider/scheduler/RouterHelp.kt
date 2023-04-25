package com.lsn.module.provider.scheduler

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.pmisy.roomkb.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 08:38
 * @Description :
 */
class RouterHelp private constructor() {

    companion object RouterPath {
        val get = Instance.routerHelp
    }


    fun actionMain() {
        ARouter.getInstance().build(Constants.RouterPath.MAIN.INDEX).navigation();
    }


    fun actionAct(path: String) {
        baseGoto(path).navigation()
    }


    fun baseGoto(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    fun actionMusicIndex() {
        ARouter.getInstance().build(Constants.RouterPath.MUSIC.MUSIC_INDEX).navigation()
    }
    fun actionVideoIndex() {
        ARouter.getInstance().build(Constants.RouterPath.VIDEO.VIDEO_INDEX).navigation()
    }
    fun actionGraphicIndex() {
        ARouter.getInstance().build(Constants.RouterPath.GRAPHIC.VIDEO_INDEX).navigation()
    }
    fun actionSettingsIndex() {
        ARouter.getInstance().build(Constants.RouterPath.SETTINGS.VIDEO_INDEX).navigation()
    }

    private object Instance {
        var routerHelp = RouterHelp()
    }
}