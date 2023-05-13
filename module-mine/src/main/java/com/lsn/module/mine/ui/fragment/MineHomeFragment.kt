package com.lsn.module.mine.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.comm.core.utils.CoilUtil
import com.lsn.module.mine.R
import com.lsn.module.mine.databinding.FragmentMineHomeBinding
import com.lsn.module.mine.ui.viewmodel.MineViewModel
import com.lsn.comm.core.constant.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.MINE.INDEX)
class MineHomeFragment :
    BaseCoreFragment<MineViewModel, FragmentMineHomeBinding>(R.layout.fragment_mine_home) {


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        CoilUtil.loadCircle(binding.ivAvatar, R.mipmap.ic_avatar)

    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
    }

}