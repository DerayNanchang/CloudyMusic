package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/2 上午 10:16
 * @Description :
 */
data class MusicPlaylistCurtRoot(
    val id: Long,
    val title: String,
    val desc: String? = "",
    val playSize: Long,
    val coverImgUrl: String,
    val userId: Long, //366231393
    val createTime: Long,
    val updateTime: Long,
    val subscribedCount: Long,
    val cloudTrackCount: Long,
    val tracksCurts: List<TracksCurt>,
)


data class TracksCurt(
    val id: Long,
    val name: String,
    val nameSupplement: String = "",
    val picUrl: String,
    val arName: String,// 歌手
    val alName: String,// 专辑
    val fee: Int,    // 是否免费
    val sq: Boolean,
    val mv: Long
)