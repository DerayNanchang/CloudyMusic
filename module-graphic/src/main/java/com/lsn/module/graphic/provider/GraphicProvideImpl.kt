package com.lsn.module.graphic.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.module.graphic.ui.fragment.GraphicHomeFragment
import com.lsn.module.provider.comm.constant.Constants
import com.lsn.module.provider.graphic.provide.GraphicProvider
import com.lsn.module.provider.scheduler.RouterHelp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
@Route(path = Constants.RouterPath.GRAPHIC.PROVIDE)
class GraphicProvideImpl : GraphicProvider {


    override fun actionGraphicIndex() {
        RouterHelp.get.actionGraphicIndex()
    }

    override fun getGraphicHomeFragment(): Fragment {
        return GraphicHomeFragment()
    }


    override fun init(context: Context?) {

    }
}