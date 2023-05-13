package com.lsn.module.music.ui.viewmodel

import android.content.Context
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.module.music.entity.StandardMusic
import com.lsn.module.music.utils.LocalMusicUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 上午 10:15
 * @Description :
 */
@HiltViewModel
class LocalMusicViewModel @Inject constructor() : BaseCoreViewModel() {


    suspend fun getLocalMusics(
        context: Context,
        success: (ArrayList<StandardMusic>) -> Unit,
        failure: (Int) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            LocalMusicUtil.scanLocalMusic(context, success, failure)
        }
    }

}