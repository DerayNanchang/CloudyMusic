package com.lsn.comm.player.api

/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 02:43
 * @Description :
 */
interface IMediaPlayer {


    /**
     *  设置资源
     */
    fun setDataSource(path: String)

    fun start()

    fun pause()

    fun stop()

    fun release()

    fun isPlaying(): Boolean

    fun duration(): Int

    fun position(): Int

    fun seekTo(msec: Int)

    fun setVolume(vol: Float)

    fun getAudioSessionId(): Int

}