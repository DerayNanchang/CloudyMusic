package com.lsn.module.music.exts

import android.text.TextUtils
import com.google.gson.Gson
import com.lsn.module.music.entity.*
import com.lsn.module.music.repository.net.impl.MusicRepositoryImpl
import com.lsn.module.music.ui.activity.TopActivity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/8 上午 09:55
 * @Description :
 */


fun MusicRepositoryImpl.topListDetailContentData(
    it: MusicTopData,
    type: Int,
    viewType: Int
): TopPlaylist {
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

    var value = ""
    if (it.playCount in 10000L..100000000L) {
        value = (it.playCount / 10000).toInt().toString() + "万"
    } else if (it.playCount > 100000000L) {
        value = (it.playCount / 100000000L).toInt().toString() + "亿"
    } else {
        value = it.playCount.toString()
    }
    var valueStr = "▷ $value"



    val topPlaylist = TopPlaylist(
        type = type,
        viewType = viewType,
        updateFrequency = it.updateFrequency,
        trackStrList = trackList,
        id = it.id,
        title = it.name,
        desc = desc,
        playSize = it.playCount,
        playCountStr = valueStr,
        coverImgUrl = it.coverImgUrl,
        trackCount = it.trackCount,
        userId = it.userId,
        createTime = it.createTime,
        updateTime = it.updateTime,
        subscribedCount = it.subscribedCount,
        cloudTrackCount = it.cloudTrackCount,
        )


    return topPlaylist
}