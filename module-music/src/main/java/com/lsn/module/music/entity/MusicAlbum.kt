package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/4/27 下午 06:51
 * @Description :
 */

data class MusicAlbumNew(
    val total: Int = 0,
    val albums: List<MusicAlbum>? = null,
    val code: Int = 0
)

data class MusicAlbum(
    val alias: List<String>,
    val artist: MusiCartist,
    val artists: List<MusiCartist>,
    val blurPicUrl: String = "",
    val briefDesc: String = "",
    val commentThreadId: String = "",
    val company: String = "",
    val companyId: Int = 0,
    val copyrightId: Int = 0,
    val description: String = "",
    val id: Int = 0,
    val name: String = "",
    val onSale: Boolean = false,
    val paid: Boolean = false,
    val pic: Long = 0L,
    val picId: Long = 0L,
    val picId_str: String = "",
    val picUrl: String = "",
    val publishTime: Long,
    val size: Int = 0,
    val songs: List<String>,
    val status: Int = 0,
    val tags: String = "",
    val type: String = ""
)

data class MusiCartist(
    val albumSize: Int = 0,
    val alias: List<String>? = null,
    val briefDesc: String = "",
    val id: Int = 0,
    val img1v1Id: Long = 0L,
    val img1v1Id_str: String = "",
    val img1v1Url: String = "",
    val musicSize: Int = 0,
    val name: String = "",
    val picId: Long = 0L,
    val picId_str: String = "",
    val picUrl: String = "",
    val topicPerson: Int = 0,
    val trans: String = ""
)