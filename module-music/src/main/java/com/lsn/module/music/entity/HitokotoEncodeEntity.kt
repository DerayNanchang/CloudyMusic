package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:53
 * @Description :
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