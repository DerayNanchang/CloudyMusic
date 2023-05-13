package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/10 下午 05:17
 * @Description :
 */
data class StandardPlaylist(
    override val id: Long,
    override val title: String,
    override val desc: String? = "",
    override val playSize: Long,
    override val playCountStr: String,
    override val coverImgUrl: String,
    override val trackCount: Long,
    override val userId: Long, //366231393
    override val createTime: Long,
    override val updateTime: Long,
    override val subscribedCount: Long,
    override val cloudTrackCount: Long,
    override val standardMusicList: List<StandardMusic>? = null,
) : BasePlaylist(
    id,
    title,
    desc,
    playSize,
    playCountStr,
    coverImgUrl,
    trackCount,
    userId,
    createTime,
    updateTime,
    subscribedCount,
    cloudTrackCount,
    standardMusicList
)


data class TopPlaylist(
    val type: Int,
    val viewType:Int,
    val updateFrequency:String,
    val trackStrList:List<String>,
    override val id: Long,
    override val title: String,
    override val desc: String? = "",
    override val playSize: Long,
    override val playCountStr: String,
    override val coverImgUrl: String,
    override val trackCount: Long,
    override val userId: Long, //366231393
    override val createTime: Long,
    override val updateTime: Long,
    override val subscribedCount: Long,
    override val cloudTrackCount: Long,
    override val standardMusicList: List<StandardMusic>? = null,
) : BasePlaylist(
    id,
    title,
    desc,
    playSize,
    playCountStr,
    coverImgUrl,
    trackCount,
    userId,
    createTime,
    updateTime,
    subscribedCount,
    cloudTrackCount,
    standardMusicList
)


data class DecUserPlaylist(
    var type: Int,
    override val id: Long,
    override val title: String,
    override val desc: String? = "",
    override val playSize: Long,
    override val playCountStr: String,
    override val coverImgUrl: String,
    override val trackCount: Long,
    override val userId: Long, //366231393
    override val createTime: Long,
    override val updateTime: Long,
    override val subscribedCount: Long,
    override val cloudTrackCount: Long,
    override val standardMusicList: List<StandardMusic>? = null,
) : BasePlaylist(
    id,
    title,
    desc,
    playSize,
    playCountStr,
    coverImgUrl,
    trackCount,
    userId,
    createTime,
    updateTime,
    subscribedCount,
    cloudTrackCount,
    standardMusicList
)


abstract class BasePlaylist(
    open val id: Long,
    open val title: String,
    open val desc: String? = "",
    open val playSize: Long,
    open val playCountStr: String,
    open val coverImgUrl: String,
    open val trackCount: Long,
    open val userId: Long, //366231393
    open val createTime: Long,
    open val updateTime: Long,
    open val subscribedCount: Long,
    open val cloudTrackCount: Long,
    open val standardMusicList: List<StandardMusic>? = null,
)