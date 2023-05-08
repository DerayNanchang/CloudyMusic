package com.lsn.module.music.exts

import android.text.TextUtils
import com.google.gson.Gson
import com.lsn.module.music.entity.MusicTopCurtData
import com.lsn.module.music.entity.MusicTopData
import com.lsn.module.music.repository.net.impl.MusicRepositoryImpl
import com.lsn.module.music.ui.activity.TopActivity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 上午 09:55
 * @Description :
 */


fun MusicRepositoryImpl.topListDetailTitleData(
    title: String,
    content: String,
    type: Int
): MusicTopCurtData {
    return MusicTopCurtData(
        id = 0L,
        name = title,
        desc = content,
        coverImgUrl = "",
        updateFrequency = "",
        trackCount = 0L,
        trackStrList = ArrayList(),
        playCount = 0L,
        tracks = null,
        type = type,
        viewType = TopActivity.VIEW_TYPE_TITLE,
    )
}

fun MusicRepositoryImpl.topListDetailContentData(
    it: MusicTopData,
    type: Int,
    viewType: Int
): MusicTopCurtData {
    var desc = ""
    if (TextUtils.isEmpty(desc)) {
        desc = "这个人很懒什么都没有留下"
    } else {
        desc = it.description!!
    }

    val trackList = ArrayList<String>()
    it.tracks?.forEach {
        trackList.add(it.first + " - " + it.second)
    }


    val musicTopCurtData = MusicTopCurtData(
        id = it.id,
        name = it.name,
        desc = desc,
        coverImgUrl = it.coverImgUrl,
        updateFrequency = it.updateFrequency,
        trackCount = it.trackCount,
        playCount = it.playCount,
        tracks = it.tracks,
        trackStrList = trackList,
        type = type,
        viewType = viewType
    )

    return musicTopCurtData
}