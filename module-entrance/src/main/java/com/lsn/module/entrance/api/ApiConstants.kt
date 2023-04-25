package com.lsn.module.entrance.api


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:52
 * @Description :
 */
interface ApiConstants {

    interface OrderBaseApis{

        companion object{
            var HITOKOTO = "https://v1.hitokoto.cn/"
            var BING = "https://www.bing.com/"
        }
    }


    interface Entrance {
        companion object {
            // 获取每日一言的数据
            const val HITOKOTO_ENCODE = "?encode=json"

            const val HP_IMAGE_ARCHIVE = "HPImageArchive.aspx"

        }
    }


}