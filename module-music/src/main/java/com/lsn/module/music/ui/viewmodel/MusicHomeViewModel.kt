package com.lsn.module.music.ui.viewmodel

import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlin.random.Random


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 下午 05:22
 * @Description :
 */
@HiltViewModel
class MusicHomeViewModel @Inject constructor(
    var iMusicRepository: IMusicRepository
) : BaseCoreViewModel() {


    fun getBanner() {
        request({
            val hpImageArchive = iMusicRepository.getBanner(ApiConstants.Music.BANNER).first()
            onSuccess(hpImageArchive)
        })
    }


    fun getPersonalized(limit: Int) {
        request({
            val relatedPlaylist =
                iMusicRepository.getPersonalized(ApiConstants.Music.PERSONALIZED, limit).first()
            onSuccess(relatedPlaylist)
        })
    }


    fun getRelatedPlaylist() {
        request({
            val relatedPlaylist =
                iMusicRepository.getRelatedPlaylist(ApiConstants.Music.RELATED_PLAYLIST).first()
            onSuccess(relatedPlaylist)
        })
    }


    fun getAlbumNewest() {
        request({
            val alBumNewest =
                iMusicRepository.getAlbumNewest(ApiConstants.Music.ALBUM_NEWEST).first()
            onSuccess(alBumNewest)
        })
    }


    fun getAlbumNew() {
        request({
            val albumNew = iMusicRepository.getAlbumNew(ApiConstants.Music.ALBUM_NEW).first()
            onSuccess(albumNew)
        })
    }


    fun getMV() {
        request({
            val albumNew = iMusicRepository.getMV(ApiConstants.Music.MV_FIRST).first()
            onSuccess(albumNew)
        })
    }

    fun getArtists() {
        request({
            val albumNew = iMusicRepository.getArtists(ApiConstants.Music.TOP_ARTISTS).first()
            onSuccess(albumNew)
        })
    }


}