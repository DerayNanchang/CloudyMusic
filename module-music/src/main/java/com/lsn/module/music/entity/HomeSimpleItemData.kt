package com.lsn.module.music.entity

data class HomeSimpleItemData(
    var id: Long,
    var img: String,
    var desc: String,
    var owner: String = "",
    var ownerId: Long = 0L,
    var playCount: Long = 0L,
)