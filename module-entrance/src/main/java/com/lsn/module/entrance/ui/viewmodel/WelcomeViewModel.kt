package com.pmisy.roomkb.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.lib.net.core.annotation.NetBaseUrlFunc
import com.lsn.lib.net.core.annotation.NetResponseFunc
import com.lsn.module.entrance.api.ApiConstants
import com.lsn.module.entrance.entity.HitokotoEncodeEntity
import com.lsn.module.entrance.repository.net.i.IEntranceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 09:08
 * @Description :
 */

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    var iEntrance: IEntranceRepository
) : BaseCoreViewModel() {


    var hitokotoEncodeEntityObs: ObservableField<HitokotoEncodeEntity?> =
        ObservableField<HitokotoEncodeEntity?>()

    @NetBaseUrlFunc("https://v1.hitokoto.cn/")
    @NetResponseFunc(false)
    fun getHitokotoEncode() {
        request({
            val responseEntity =
                iEntrance.getHitokotoEncode(ApiConstants.Comm.HITOKOTO_ENCODE,).first()
            var hitokotoEncodeEntity = responseEntity as HitokotoEncodeEntity
            hitokotoEncodeEntityObs.set(hitokotoEncodeEntity)
            onSuccess(responseEntity)
        })

    }


}