package com.lsn.module.music.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseNetViewModel
import com.lsn.module.music.entity.DecUserPlaylist
import com.lsn.module.music.entity.PlaylistTitle
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/5/3 下午 06:43
 * @Description :
 */
@HiltViewModel
class MeReadyViewModel @Inject constructor(
    var iMusicRepository: IMusicRepository
) : BaseNetViewModel() {

    var playlistTitleData: ObservableField<ArrayList<PlaylistTitle>> =
        ObservableField<ArrayList<PlaylistTitle>>()


    var playlistContentData: ObservableField<ArrayList<ArrayList<DecUserPlaylist>>> =
        ObservableField<ArrayList<ArrayList<DecUserPlaylist>>>()


    fun getHitokotoEncode() {
        request({
            val responseEntity =
                iMusicRepository.getHitokotoEncode(ApiConstants.Music.HITOKOTO_ENCODE).first()
            onSuccess(responseEntity)
        }, isShowDialog = false)
    }

    fun getPlaylistDetail(uid: Long) {

        request({
            val hpImageArchive =
                iMusicRepository.getUserPlaylist(ApiConstants.Music.USER_PLAYLIST, uid, 0, 0)
                    .first()
            val musicPlaylistCurtRoot = hpImageArchive.data as ArrayList<DecUserPlaylist>


            val contentArrayList = ArrayList<ArrayList<DecUserPlaylist>>()

            val titleArrayList = ArrayList<PlaylistTitle>().also {
                musicPlaylistCurtRoot.apply {
                    val meTitleList = filter { it.type == 0 }
                    val otherTitleList = filter { it.type != 0 }
                    contentArrayList.add(meTitleList as ArrayList<DecUserPlaylist>)
                    contentArrayList.add(otherTitleList as ArrayList<DecUserPlaylist>)
                    it.add(
                        PlaylistTitle(
                            0L,
                            "创建的歌单",
                            meTitleList.size.toString(),
                            meTitleList.size
                        )
                    )
                    it.add(
                        PlaylistTitle(
                            id = 0L,
                            title = "收藏的歌单",
                            otherTitleList.size.toString(),
                            otherTitleList.size
                        )
                    )
                }
            }
            playlistTitleData.set(titleArrayList)
            playlistContentData.set(contentArrayList)

            onSuccess(hpImageArchive)
        })
    }
}