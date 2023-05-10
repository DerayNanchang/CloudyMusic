package com.lsn.module.music.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.module.music.entity.StandardPlaylist
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

    var standardPlaylist: ObservableField<StandardPlaylist?> = ObservableField<StandardPlaylist?>()



    fun getPlaylistDetail(id:Long) {

        request({
            val hpImageArchive = iMusicRepository.getPlaylistDetail(ApiConstants.Music.PLAYLIST_DETAIL,id).first()
            val mStandardPlaylist = hpImageArchive.data as StandardPlaylist
            standardPlaylist.set(mStandardPlaylist)
            onSuccess(hpImageArchive)
        })
    }


    fun getTest(){
        request({
            iMusicRepository.getToplistDetail(ApiConstants.Music.TOPLIST_DETAIL).first()
        })
    }
}