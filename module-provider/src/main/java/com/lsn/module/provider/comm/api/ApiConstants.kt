package com.lsn.module.provider.comm.api

import retrofit2.http.GET


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
            const val HP_IMAGE_ARCHIVE = "HPImageArchive.aspx"


            /**
             *  说明: 二维码登录涉及到 3 个接口,调用务必带上时间戳,防止缓存
             *  1. 二维码 key 生成接口
             *  说明: 调用此接口可生成一个 key
             */
            const val LOGIN_QR_KEY = "login/qr/key"
            /**
             *  说明: 调用此接口传入上一个接口生成的 key 可生成二维码图片的 base64 和二维码信息,可使用 base64 展示图片,或者使用二维码信息内容自行使用第三方二维码生成库渲染二维码
             *  必选参数: key,由第一个接口生成
             *  可选参数: qrimg 传入后会额外返回二维码图片 base64 编码
             *  接口地址 : /login/qr/create
             *  调用例子 : /login/qr/create?key=xxx
             */
            const val LOGIN_QR_CREATE = "login/qr/create"
            /**
             *  说明: 轮询此接口可获取二维码扫码状态,800 为二维码过期,801 为等待扫码,802 为待确认,803 为授权登录成功(803 状态码下会返回 cookies)
             *  必选参数: key,由第一个接口生成
             *  接口地址 : /login/qr/check
             *  调用例子 : /login/qr/check?key=xxx
             */
            const val LOGIN_QR_CHECK = "login/qr/check"
        }
    }


    interface Music {

        companion object {
            const val HITOKOTO_ENCODE = "?encode=json"

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


            /**
             *  说明 : 调用此接口,可获取所有榜单内容摘要
            接口地址 : /toplist/detail
            调用例子 : /toplist/detail
             */
            const val TOPLIST_DETAIL = "toplist/detail"


            /**
             * 说明 : 登录后调用此接口 , 传入用户 id, 可以获取用户歌单
             * 必选参数 : uid : 用户 id
             * 可选参数 :
             * limit : 返回数量 , 默认为 30
             * offset : 偏移数量，用于分页 , 如 :( 页数 -1)*30, 其中 30 为 limit 的值 , 默认为 0
             * 接口地址 : /user/playlist
             * 调用例子 : /user/playlist?uid=32953014
             */
            const val USER_PLAYLIST = "user/playlist"

            /**
             *  说明 : 歌单能看到歌单名字, 但看不到具体歌单内容 , 调用此接口 , 传入歌单 id, 可 以获取对应歌单内的所有的音乐(未登录状态只能获取不完整的歌单,登录后是完整的)，但是返回的 trackIds 是完整的，tracks 则是不完整的，可拿全部 trackIds 请求一次 song/detail 接口获取所有歌曲的详情 (https://github.com/Binaryify/NeteaseCloudMusicApi/issues/452)
             *  必选参数 : id : 歌单 id
             *  可选参数 : s : 歌单最近的 s 个收藏者,默认为 8
             *  接口地址 : /playlist/detail
             *  调用例子 : /playlist/detail?id=24381616
             */
            const val PLAYLIST_DETAIL = "playlist/detail"

        }

    }


}