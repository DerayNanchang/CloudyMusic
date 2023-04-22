package com.lsn.module.provider.scheduler

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter


/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 08:38
 * @Description :
 */
class RouterHelp private constructor() {

    companion object RouterPath {
        val get = Instance.routerHelp
        private const val MAIN = "/main"
        const val MAIN_INDEX = "$MAIN/index"
        const val MAIN_PROVIDE = "$MAIN/provider"


        private const val ENTRANCE = "/entrance"
        const val ENTRANCE_AUTH = "$ENTRANCE/auth"
        const val ENTRANCE_WELCOME = "$ENTRANCE/welcome"
    }


    fun actionMain() {
        ARouter.getInstance().build(MAIN_INDEX).navigation();
//        actionAct(MAIN_INDEX)
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