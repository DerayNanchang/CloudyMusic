package com.lsn.comm.player.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import com.lsn.comm.player.api.IMusicMediaPlayer
import com.lsn.comm.player.impl.NativeMusicMediaPlayer
import java.lang.ref.WeakReference


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 03:18
 * @Description :
 */
class MediaPlayerService : Service(), IMusicMediaPlayer {

    private var mPlayer: NativeMusicMediaPlayer? = null

    companion object {

        val TRACK_WENT_TO_NEXT = 2 //下一首

        val RELEASE_WAKELOCK = 3 //播放完成

        val TRACK_PLAY_ENDED = 4 //播放完成

        val TRACK_PLAY_ERROR = 5 //播放出错


        val PREPARE_ASYNC_UPDATE = 7 //PrepareAsync装载进程

        val PLAYER_PREPARED = 8 //mediaplayer准备完成

        val AUDIO_FOCUS_CHANGE = 12 //音频焦点改变

        val VOLUME_FADE_DOWN = 13 //音量改变减少

        val VOLUME_FADE_UP = 14 //音量改变增加
    }


    private var mWorkThread: HandlerThread? = null
    private var mHandler: MusicPlayerHandler? = null



    class MusicPlayerHandler constructor(service: MediaPlayerService, looper: Looper) : Handler(looper) {
        private var mService : WeakReference<MediaPlayerService>
        init {
            mService = WeakReference<MediaPlayerService>(service)
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){

                TRACK_WENT_TO_NEXT ->{

                }
                RELEASE_WAKELOCK ->{

                }
                TRACK_PLAY_ENDED ->{

                }
                TRACK_PLAY_ERROR ->{

                }
                PREPARE_ASYNC_UPDATE ->{

                }
                PLAYER_PREPARED ->{

                }
                TRACK_WENT_TO_NEXT ->{

                }




            }


        }


    }


    override fun onCreate() {
        super.onCreate()

        initThread()
        initMediaPlayer()
    }

    private fun initThread() {
        //初始化工作线程
        mWorkThread = HandlerThread("MusicPlayerThread")
        mWorkThread!!.start()
        mHandler = MusicPlayerHandler(this, mWorkThread!!.looper)
    }

    override fun onBind(intent: Intent?): IBinder? {

    }


    /**
     * 初始化音乐播放服务
     */
    private fun initMediaPlayer() {
        mPlayer = NativeMusicMediaPlayer(this)
        mPlayer?.let {
            it.setHandler(mHandler!!)
            reloadPlayQueue()
        }
    }


    override fun setDataSource(path: String) {


    }


    override fun play() {
    }

    override fun pause() {
    }

    override fun playPause() {
    }

    override fun netPlay() {
    }

    override fun proPlay() {
    }

    override fun seekTo() {
    }
}