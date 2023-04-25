package com.lsn.module.entrance.entity

/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 上午 09:51
 * @Description :
 */


data class HPImageArchiveEntity(
    var images: List<HPImageArchiveImagesEntity>,
    var tooltips: HPImageArchiveTooltipsEntity
)

data class HPImageArchiveTooltipsEntity(
    val loading: String,
    val next: String,
    val previous: String,
    val walle: String,
    val walls: String
)


data class HPImageArchiveImagesEntity(
    val bot: Int,
    val copyright: String,
    val copyrightlink: String,
    val drk: Int,
    val enddate: String,
    val fullstartdate: String,
    val hs: List<String>,
    val hsh: String,
    val quiz: String,
    val startdate: String,
    val title: String,
    val top: Int,
    var url: String,
    val urlbase: String,
    val wp: Boolean,
    var mICopyright:String,
    var desc:String
)


/**
 *  每日一言的接口数据
 */
data class HitokotoEncodeEntity(
    val commit_from: String,
    val created_at: String,
    val creator: String,
    val creator_uid: Int,
    val from: String,
    val from_who: Any,
    val hitokoto: String,
    val id: Int,
    val length: Int,
    val reviewer: Int,
    val type: String,
    val uuid: String
)