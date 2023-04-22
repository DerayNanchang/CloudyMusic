package com.lsn.module.provider

import android.content.Context


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