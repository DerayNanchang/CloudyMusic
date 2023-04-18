package com.pmisy.roomkb.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.comm.KBORRoomEntity
import com.pmisy.roomkb.entity.comm.KBOrRoomBottomDetailEntity
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
class InjORRoomViewModel @Inject
constructor(
    var iCommRepository: ICommRepository,
    var userInfo: UserInfEntity?,
) : BaseCoreViewModel() {

    var bottomData: ObservableField<KBOrRoomBottomDetailEntity?> =
        ObservableField<KBOrRoomBottomDetailEntity?>()


    fun getKBEQBodyData(plantNo: String, room: String, queryDate: String) {
        userInfo?.let {
            request({
                val bodyData = iCommRepository.searchProdOrderDetailList(
                    ApiConstants.Comm.SEARCH_PROD_ORDER_DETAIL_LIST,
                    it.clientNo, plantNo, room, queryDate
                ).first()
                val kbEqRoomEntity = bodyData.data as KBORRoomEntity
                val map = kbEqRoomEntity.map
                bottomData.set(map)
                onSuccess(bodyData)
            })
        }
    }


}