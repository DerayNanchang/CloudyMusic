package com.pmisy.roomkb.ui.viewmodel

import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.login.UserInfEntity
import com.pmisy.roomkb.repository.net.i.ICommRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 下午 06:06
 * @Description :
 */
@HiltViewModel
class InjRoomViewModel @Inject constructor(
    var iCommRepository: ICommRepository,
    var userInfo: UserInfEntity?,
) : BaseCoreViewModel() {

    fun getRoomData(plantNo: String, routeType: String) {
        userInfo?.let {
            request({
                val data = iCommRepository.searchRoom(
                    ApiConstants.Comm.SEARCH_ROOM,
                    it.clientNo,
                    it.languageNo,
                    plantNo,
                    routeType
                ).first()
                onSuccess(data)
            })
        }
    }

}