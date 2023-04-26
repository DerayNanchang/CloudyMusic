package com.lsn.module.graphic.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.module.graphic.R
import com.lsn.module.graphic.databinding.FragmentGraphicHomeBinding
import com.lsn.module.graphic.ui.viewmodel.GraphicViewModel
import com.pmisy.roomkb.Constants


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:21
 * @Description :
 */
@Route(path = Constants.RouterPath.GRAPHIC.INDEX)
class GraphicHomeFragment :
    BaseCoreFragment<GraphicViewModel, FragmentGraphicHomeBinding>(R.layout.fragment_graphic_home) {

}