package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/4 上午 08:08
 * @Description :
 */
data class PlaylistTitle(
    var id: Long,
    var title: String,
    var desc: String,
    var count: Int,
)


/*
data class Playlist(
    val id: Long,
    val name: String,
    val desc: String? = "这个人很懒，什么都没有留下",
    val picUrl: String,
    val ownerId: Long,
    val ownerName: String,
    val trackCount: Long,
    val playCount: Long,
    val type: Int,
)*/
