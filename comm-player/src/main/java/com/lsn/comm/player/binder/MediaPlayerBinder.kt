package com.lsn.comm.player.binder

import android.os.Binder
import com.lsn.comm.player.service.MediaPlayerService


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 03:32
 * @Description :
 */
class MediaPlayerBinder(var mediaPlayerService: MediaPlayerService) : Binder() {




}