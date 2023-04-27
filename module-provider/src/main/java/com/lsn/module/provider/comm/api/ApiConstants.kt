package com.lsn.module.provider.comm.api


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:52
 * @Description :
 */
interface ApiConstants {

    interface OrderBaseApis {

        companion object {
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


    interface Music {

        companion object {

            // 获取 banner
            const val BANNER = "banner"

            // 调用此接口 ，获取云音乐首页新碟上架数据
            const val ALBUM_NEWEST = "album/newest"


            /**
             * 登录后调用此接口 ,可获取全部新碟
             * limit : 返回数量 , 默认为 30
             * offset : 偏移数量，用于分页 , 如 :( 页数 -1)*30, 其中 30 为 limit 的值 , 默认为 0
             * area : ALL:全部,ZH:华语,EA:欧美,KR:韩国,JP:日本
             */
            const val ALBUM_NEW = "album/new?area=KR&limit=10"


        }

    }


}