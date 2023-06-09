package com.lsn.module.provider.entrance.provide

import androidx.fragment.app.Fragment
import com.lsn.module.provider.BaseProvider

/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 08:25
 * @Description :
 */
interface EntranceProvider : BaseProvider {

    fun actionEntranceIndex()

    fun getEntranceHomeFragment() : Fragment
}