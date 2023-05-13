package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/9 下午 04:40
 * @Description :
 */
data class StandardMusic(
    var source: SourceKind = SourceKind.LOCAL, // 歌曲来源，本地，网络
    var id: Long = 0L, // 歌曲 id
    var name: String = "", // 歌曲名称
    var imageUrl: String = "", // 图片 url
    var ar: ArrayList<StandardAR>?, // 艺术家
    var arNames: String = "",
    var al: StandardAl, // 专辑
    var neteaseInfo: NetInfo?,
    var localInfo: LocalInfo?,
)


enum class SourceKind {
    LOCAL, NET
}


data class StandardAR(
    val arId: Long = 0L, // 艺术家 id
    val name: String = "" // 艺术家名称
)

data class StandardAl(
    val alId: Long = 0L, // 专辑 id
    val name: String = "", // 专辑名称
    val picUrl: String = ""
)


/**
 *  网络音乐
 */
data class NetInfo(
    val fee: Int = 0, // 是否VIP歌曲 1Vip
    val pl: Int = 1, // 0 为无效歌曲，是否有版权
    val maxbr: Int = 0 // 最大音质
)


/**
 *  本地音乐
 */
data class LocalInfo(
    val size: Long = 0L,
    var data: String = "", // 文件路径
)