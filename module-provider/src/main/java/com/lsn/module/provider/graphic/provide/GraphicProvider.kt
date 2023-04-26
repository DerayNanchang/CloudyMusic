package com.lsn.module.provider.graphic.provide

import androidx.fragment.app.Fragment
import com.lsn.module.provider.BaseProvider

/**
 * @Author : lsn
 * @CreateTime : 2023/4/26 上午 11:23
 * @Description :
 */
interface GraphicProvider : BaseProvider {

    fun actionGraphicIndex()

    fun getGraphicHomeFragment() : Fragment
}