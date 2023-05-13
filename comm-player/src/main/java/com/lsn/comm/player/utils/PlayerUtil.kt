package com.lsn.comm.player.utils

import com.danikula.videocache.HttpProxyCacheServer
import java.io.File

object PlayerUtil {

    private val proxy: HttpProxyCacheServer? = null

    fun getProxy(): HttpProxyCacheServer? {
        return if (com.cyl.musiclake.MusicApp.getInstance().proxy == null) com.cyl.musiclake.MusicApp.getInstance()
            .newProxy().also {
                com.cyl.musiclake.MusicApp.getInstance().proxy = it
            } else com.cyl.musiclake.MusicApp.getInstance().proxy
    }

    private fun newProxy(): HttpProxyCacheServer? {
        return HttpProxyCacheServer.Builder(this)
            .cacheDirectory(File(FileUtils.getMusicCacheDir()))
            .fileNameGenerator(CacheFileNameGenerator())
            .build()
    }

}