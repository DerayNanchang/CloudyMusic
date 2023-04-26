package com.lsn.module.mine.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.module.provider.scheduler.RouterHelp
import com.lsn.module.mine.ui.fragment.MineHomeFragment
import com.lsn.module.provider.comm.constant.Constants
import com.lsn.module.provider.mine.provide.MineProvider


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:49
 * @Description :
 */
@Route(path = Constants.RouterPath.MINE.PROVIDE)
class MineProvideImpl : MineProvider {
    override fun actionMineIndex() {
        RouterHelp.get.actionMineIndex()
    }

    override fun getMineHomeFragment(): Fragment {
        return MineHomeFragment()
    }

    override fun init(context: Context?) {

    }
}