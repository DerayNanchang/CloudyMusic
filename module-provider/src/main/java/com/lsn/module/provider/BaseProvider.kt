package com.lsn.module.provider

import com.alibaba.android.arouter.facade.template.IProvider


/**
 * @Author : lsn
 * @CreateTime : 2023/4/20 上午 08:24
 * @Description :
 */
interface BaseProvider : IProvider {
    fun actionPath(){}
    fun actionPath(path: String){}
    fun actionPath(parameter: Map<String, String>){}
    fun actionPath(path: String, parameter: Map<String, String>){}
}