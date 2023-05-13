package com.lsn.comm.player.api

/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 02:45
 * @Description :
 */
interface IMusicMediaPlayer  {

    /**
     *  设置资源
     */
    fun setDataSource(path: String)


    /**
     * 播放
     */
    fun play()


    /**
     * 暂停
     */
    fun pause()


    /**
     * 播放暂替
     */
    fun playPause()


    /**
     *  下一个资源
     */
    fun netPlay()


    /**
     *  上一个资源
     */
    fun proPlay()


    /**
     * 输入进度
     */
    fun seekTo()
}