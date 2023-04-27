package com.lsn.module.music.entity


data class MusicArtistsRoot(

    val artists: List<MusicArtists>,

    val more: Boolean,
    val code: Int
)

data class MusicArtists(
    val accountId: Any,
    val albumSize: Int,
    val alg: String,
    val alias: List<String>,
    val briefDesc: String,
    val fansCount: Int,
    val followed: Boolean,
    val id: Int,
    val identifyTag: String,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val isSubed: String,
    val musicSize: Int,
    val mvSize: String,
    val name: String,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: String,
    val showPrivateMsg: String,
    val topicPerson: Int,
    val trans: String,
    val transNames: String
)