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
             * 说明 : 调用此接口 , 可获取推荐歌单
             * 可选参数 : limit: 取出数量 , 默认为 30 (不支持 offset)
             * 接口地址 : /related/playlist
             */
            const val PERSONALIZED = "personalized"


            /**
             * 说明 : 调用此接口,传入歌单 id 可获取相关歌单(对应页面 https://music.163.com/#/playlist?id=1)
             * 必选参数 : id : 歌单 id
             * 接口地址 : /related/playlist
             */
            const val RELATED_PLAYLIST = "related/playlist?id=1"


            /**
             * 登录后调用此接口 ,可获取全部新碟
             * limit : 返回数量 , 默认为 30
             * offset : 偏移数量，用于分页 , 如 :( 页数 -1)*30, 其中 30 为 limit 的值 , 默认为 0
             * area : ALL:全部,ZH:华语,EA:欧美,KR:韩国,JP:日本
             */
            const val ALBUM_NEW = "album/new?area=KR&limit=10"


            /**
             * 调用此接口 , 可获取最新 mv
             * 可选参数 : area: 地区,可选值为全部,内地,港台,欧美,日本,韩国,不填则为全部
             * 可选参数 : limit: 取出数量 , 默认为 30
             * 接口地址 : /mv/first
             */
            const val MV_FIRST = "mv/first?limit=10"


            /**
             * 调用此接口 , 可获取热门歌手数据
             * 可选参数 : limit: 取出数量 , 默认为 50
             * offset: 偏移数量 , 用于分页 , 如 :( 页数 -1)*50, 其中 50 为 limit 的值 , 默认 为 0
             * 接口地址 : /top/artists
             */
            const val TOP_ARTISTS = "top/artists?offset=0&limit=30"

        }

    }


}