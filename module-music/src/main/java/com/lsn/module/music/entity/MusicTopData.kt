package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/6 上午 08:55
 * @Description :
 */

data class MusicTopRoot(
    val code: Int,
    val list: List<MusicTopData>,
    val artistToplist: ArtistToplist,
    val rewardToplist: Any,
)


data class MusicTopCurtData(
    var id: Long,
    var name: String,
    var desc: String = "",
    var coverImgUrl: String,
    var updateFrequency: String,
    var trackCount: Long,
    var playCount: Long,
    var tracks: List<TopArtist>?,
    var trackStrList: List<String>,
    var type: Int,
    var viewType: Int,
)


data class MusicTopData(
    val ToplistType: String,
    val adType: Int,
    val anonimous: Boolean,
    val artists: Any,
    val backgroundCoverId: Long,
    val backgroundCoverUrl: Any,
    val cloudTrackCount: Long,
    val commentThreadId: String,
    val coverImgId: Long,
    val coverImgId_str: String,
    val coverImgUrl: String,
    val coverText: Any,
    val createTime: Long,
    val creator: Any,
    val description: String?,
    val englishTitle: Any,
    val highQuality: Boolean,
    val id: Long,
    val name: String,
    val newImported: Boolean,
    val opRecommend: Boolean,
    val ordered: Boolean,
    val playCount: Long,
    val privacy: Int,
    val recommendInfo: Any,
    val socialPlaylistCover: Any,
    val specialType: Int,
    val status: Int,
    val subscribed: Any,
    val subscribedCount: Long,
    val subscribers: List<Any>,
    val tags: List<Any>,
    val titleImage: Long,
    val titleImageUrl: Any,
    val totalDuration: Int,
    val trackCount: Long,
    val trackNumberUpdateTime: Long,
    val trackUpdateTime: Long,
    val tracks: List<TopArtist>?,
    val updateFrequency: String,
    val updateTime: Long,
    val userId: Long
)


data class TopArtist(
    val first: String,
    val second: String
)


data class ArtistToplist(
    val artists: List<TopArtist>,
    val coverUrl: String,
    val name: String,
    val position: Int,
    val upateFrequency: String,
    val updateFrequency: String
)
