package com.lsn.module.music.entity


data class MusicMVRoot(

    val data: List<MusicMVs>,
    val code: Int


)


data class MusicMVs(
    val artistId: Int,
    val artistName: String,
    val artists: List<Artist>,
    val briefDesc: String,
    val cover: String,
    val desc: String,
    val duration: Int,
    val id: Long,
    val mark: Int,
    val name: String,
    val playCount: Int,
    val subed: Boolean
)

data class Artist(
    val id: Int,
    val name: String
)