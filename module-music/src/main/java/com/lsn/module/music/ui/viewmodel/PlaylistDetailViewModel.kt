package com.lsn.module.music.ui.viewmodel

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.module.music.entity.MusicPlaylistCurtRoot
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 下午 03:29
 * @Description :
 */
@HiltViewModel
class PlaylistDetailViewModel@Inject constructor(
    var iMusicRepository: IMusicRepository
)  : BaseNetViewModel() {

    var playlistCurtData: ObservableField<MusicPlaylistCurtRoot?> = ObservableField<MusicPlaylistCurtRoot?>()

    fun getPlaylistDetail(id:Long) {



        request({
            val hpImageArchive = iMusicRepository.getPlaylistDetail(ApiConstants.Music.PLAYLIST_DETAIL,id).first()
            val musicPlaylistCurtRoot = hpImageArchive.data as MusicPlaylistCurtRoot
            playlistCurtData.set(musicPlaylistCurtRoot)
            onSuccess(hpImageArchive)
        })
    }
}