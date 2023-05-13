package com.lsn.comm.player.impl

import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.os.PowerManager
import com.danikula.videocache.HttpProxyCacheServer
import com.lsn.comm.core.constant.Constants
import com.lsn.comm.core.utils.MMKVUtil
import com.lsn.comm.player.service.MediaPlayerService
import com.lsn.comm.player.service.MediaPlayerService.Companion.PLAYER_PREPARED
import com.lsn.comm.player.service.MediaPlayerService.Companion.PREPARE_ASYNC_UPDATE
import com.lsn.comm.player.service.MediaPlayerService.Companion.RELEASE_WAKELOCK
import com.lsn.comm.player.service.MediaPlayerService.Companion.TRACK_PLAY_ENDED
import com.lsn.comm.player.service.MediaPlayerService.Companion.TRACK_PLAY_ERROR
import com.lsn.comm.player.service.MediaPlayerService.Companion.TRACK_WENT_TO_NEXT
import java.lang.ref.WeakReference


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 03:04
 * @Description :
 */
class NativeMusicMediaPlayer(service: MediaPlayerService) : NativeMediaPlayer(), OnErrorListener,
    OnCompletionListener, OnBufferingUpdateListener, OnPreparedListener {


    private var mCurrentMediaPlayer = MediaPlayer()

    private var weakService: WeakReference<MediaPlayerService>
    private var mIsInitialized: Boolean = false
    private var mIsPrepared = false
    private var mHandler: MediaPlayerService.MusicPlayerHandler? = null

    init {
        weakService = WeakReference<MediaPlayerService>(service)
        mCurrentMediaPlayer.setWakeMode(weakService.get(), PowerManager.PARTIAL_WAKE_LOCK)
    }

    override fun setDataSource(path: String) {
        mIsInitialized = setDataSourceImpl(mCurrentMediaPlayer, path)
    }


    fun setDataSourceImpl(player: MediaPlayer, path: String): Boolean {
        if (path == null) return false
        try {
            if (player.isPlaying()) player.stop()
            mIsPrepared = false
            player.reset()

            val isCache = MMKVUtil.getBoolean(Constants.Comm.CACHE_MODE, true)

//            LogUtil.d(TAG, "缓存设置：$cacheSetting")
            //本地歌曲无需缓存
            if (path.startsWith("content://") || path.startsWith("/storage")) {
                weakService.get()?.let { player.setDataSource(it, Uri.parse(path)) }
            } else if (isCache) {
                //缓存开启，读取缓存
                val proxy: HttpProxyCacheServer = MusicApp.getProxy()
                val proxyUrl: String = proxy.getProxyUrl(path)
//                LogUtil.d(TAG, "设置缓存,缓存地址：proxyUrl=$proxyUrl")
                player.setDataSource(proxyUrl)
            } else {
                //不缓存
                player.setDataSource(path)
            }
            player.setOnPreparedListener(this)
            player.setOnBufferingUpdateListener(this)
            player.setOnErrorListener(this)
            player.setOnCompletionListener(this)
            player.prepareAsync()
        } catch (todo: Exception) {
            todo.printStackTrace()
            return false
        }
        return true
    }


    fun isInitialized(): Boolean {
        return mIsInitialized
    }

    fun isPrepared(): Boolean {
        return mIsPrepared
    }


    override fun start() {
        mCurrentMediaPlayer.start()
    }

    override fun pause() {
        mCurrentMediaPlayer.pause()
    }

    override fun stop() {
        mCurrentMediaPlayer.stop()
    }

    override fun release() {
        mCurrentMediaPlayer.release()
    }

    override fun isPlaying() = mCurrentMediaPlayer.isPlaying

    override fun duration(): Int {
        return if (mIsPrepared) {
            mCurrentMediaPlayer.duration
        } else 0
    }

    override fun position(): Int {
        return try {
            mCurrentMediaPlayer.currentPosition
        } catch (e: IllegalStateException) {
            -1
        }
    }

    override fun seekTo(msec: Int) {
        mCurrentMediaPlayer.seekTo(msec)
    }

    override fun setVolume(vol: Float) {
//        LogUtil.e("Volume", "vol = $vol")
        try {
            mCurrentMediaPlayer.setVolume(vol, vol)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun getAudioSessionId(): Int {
        return mCurrentMediaPlayer.audioSessionId
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        when (what) {
            MEDIA_ERROR_SERVER_DIED, MEDIA_ERROR_UNKNOWN -> {

                if (mHandler != null && weakService.get() != null) {
                    val service: MediaPlayerService = weakService.get()!!
                    val errorInfo: TrackErrorInfo =
                        TrackErrorInfo(
                            0L,
                            ""
                        )
                    mIsInitialized = false
                    //播放错误，需要重新释放mediaPlayer
                    mCurrentMediaPlayer.release()
                    mCurrentMediaPlayer = MediaPlayer()
                    mCurrentMediaPlayer.setWakeMode(service, PowerManager.PARTIAL_WAKE_LOCK)
                    val msg: Message = mHandler!!.obtainMessage(TRACK_PLAY_ERROR, errorInfo)
                    mHandler!!.sendMessageDelayed(msg, 500)
                }
                return true
            }
            else -> {}
        }
        return true

    }

    /**
     *  结束调用
     */
    override fun onCompletion(mp: MediaPlayer?) {
        mp?.let {
            if (mHandler != null && weakService.get() != null) {
                if (mp === mCurrentMediaPlayer) {
                    mHandler!!.sendEmptyMessage(TRACK_WENT_TO_NEXT)
                } else {
//                    weakService.get()!!.mWakeLock.acquire(30000)
                    mHandler!!.sendEmptyMessage(TRACK_PLAY_ENDED)
                    mHandler!!.sendEmptyMessage(RELEASE_WAKELOCK)
                }
            }
        }
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {

        mHandler?.let {
            val message: Message = it.obtainMessage(PREPARE_ASYNC_UPDATE, percent)
            it.sendMessage(message)
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {

        mp?.let {
            it.start()
            if (!mIsPrepared) {
                mHandler?.let {
                    mIsPrepared = true
                    val message: Message = it.obtainMessage(PLAYER_PREPARED)
                    it.sendMessage(message)
                }
            }
        }
    }


    fun setHandler(handler: MediaPlayerService.MusicPlayerHandler) {
        mHandler = handler
    }


    data class TrackErrorInfo(
        val audioId: Long = 0,
        var audioName: String = ""
    )


}