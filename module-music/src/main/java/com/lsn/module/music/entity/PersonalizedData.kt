package com.lsn.module.music.entity

/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 上午 09:44
 * @Description :
 */

data class MusicPersonalizedRoot(

    val hasTaste: Boolean,
    val code: Int,
    val category: Int,
    val result: List<MusicPersonalized>,

    )


data class MusicPersonalized(
    val alg: String,
    val canDislike: Boolean,
    val copywriter: String,
    val highQuality: Boolean,
    val id: Long,
    val name: String,
    val picUrl: String,
    val playCount: Long,
    val trackCount: Long,
    val trackNumberUpdateTime: Long,
    val type: Int,
    var playCountStr: String,
)