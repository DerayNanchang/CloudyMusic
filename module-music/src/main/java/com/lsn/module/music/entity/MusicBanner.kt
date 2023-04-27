package com.lsn.module.music.entity

/**
 * @Author : lsn
 * @CreateTime : 2023/4/27 上午 11:35
 * @Description :
 */


data class MusicBannerList(
    var banners: List<MusicBannerItem>? = null,
    var code: Int
)

data class MusicBannerItem(
    var adDispatchJson: String = "",
    var adLocation: String = "",
    var adSource: String = "",
    var adid: String = "",
    var bannerBizType: String = "",
    var encodeId: String = "",
    var event: String = "",
    var exclusive: Boolean,
    var extMonitor: String = "",
    var extMonitorInfo: String = "",
    var imageUrl: String = "",
    var monitorBlackList: String = "",
    var monitorClick: String = "",
    var monitorClickList: String = "",
    var monitorImpress: String = "",
    var monitorImpressList: String = "",
    var monitorType: String = "",
    var program: String = "",
    var scm: String = "",
    var song: String = "",
    var targetId: Long,
    var targetType: Int,
    var titleColor: String = "",
    var typeTitle: String = "",
    var url: String = "",
    var video: String = ""
)