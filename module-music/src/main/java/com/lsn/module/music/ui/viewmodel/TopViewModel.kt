package com.lsn.module.music.ui.viewmodel

import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.provider.comm.api.ApiConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/5/6 上午 10:07
 * @Description :
 */
@HiltViewModel
class TopViewModel @Inject constructor(
    var iMusicRepository: IMusicRepository
) : BaseCoreViewModel() {




    fun getTop(){
        request({
            val data = iMusicRepository.getToplistDetail(ApiConstants.Music.TOPLIST_DETAIL).first()
            onSuccess(data)
        })

    }


}