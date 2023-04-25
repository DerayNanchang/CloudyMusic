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

    private object Instance {
        var routerHelp = RouterHelp()
    }
}