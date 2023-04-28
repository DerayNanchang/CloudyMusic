package com.lsn.module.music.entity

/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 上午 08:11
 * @Description :
 */


data class MusicRelatedPlaylistRoot(

    val playlists: List<MusicRelatedPlaylist>,
    val code: Int

)

data class MusicRelatedPlaylist(
    val coverImgUrl: String,
    val creator: RelatedPlaylistCreator,
    val id: Long,
    val name: String
)

data class RelatedPlaylistCreator(
    val nickname: String,
    val userId: Long
)