package com.pmisy.roomkb.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.comm.KBEqRoomEntity
import com.pmisy.roomkb.entity.comm.KBEqRoomBottomDetailEntity
import com.pmisy.roomkb.entity.login.UserInfEntity
import com.pmisy.roomkb.repository.net.i.ICommRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 下午 06:42
 * @Description :
 */
@HiltViewModel
class InjEQRoomViewModel @Inject
constructor(
    var iCommRepository: ICommRepository,
    var userInfo: UserInfEntity?,
) : BaseCoreViewModel() {

    var bottomData: ObservableField<KBEqRoomBottomDetailEntity?> =
        ObservableField<KBEqRoomBottomDetailEntity?>()


    fun getKBEQBodyData(plantNo: String, room: String, queryDate: String) {
        userInfo?.let {
            request({
                val bodyData = iCommRepository.searchProdEquipmentDetailList(
                    ApiConstants.Comm.SEARCH_PROD_EQUIPMENT_DETAIL_LIST,
                    it.clientNo, plantNo, room, queryDate
                ).first()
                val kbEqRoomEntity = bodyData.data as KBEqRoomEntity
                val map = kbEqRoomEntity.map
                bottomData.set(map)
                onSuccess(bodyData)
            })
        }
    }


}