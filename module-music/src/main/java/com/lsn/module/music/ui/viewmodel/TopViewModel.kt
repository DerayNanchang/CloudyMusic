package com.lsn.module.music.ui.viewmodel

import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.module.music.repository.net.i.IMusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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




}