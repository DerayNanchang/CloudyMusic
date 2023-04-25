package com.lsn.module.entrance.ui.viewmodel

import androidx.databinding.ObservableField
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.lsn.module.provider.comm.api.ApiConstants
import com.lsn.module.entrance.entity.HPImageArchiveEntity
import com.lsn.module.entrance.entity.HPImageArchiveImagesEntity
import com.lsn.module.entrance.repository.net.i.IEntranceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlin.random.Random


/**
 * @Author : lsn
 * @CreateTime : 2023/4/25 上午 10:44
 * @Description :
 */
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    var iEntrance: IEntranceRepository
) : BaseCoreViewModel() {

    var archiveImagesEntity: ObservableField<HPImageArchiveImagesEntity?> = ObservableField<HPImageArchiveImagesEntity?>()


    fun getHitokotoEncode() {
        request({
            val responseEntity =
                iEntrance.getHitokotoEncode(ApiConstants.Entrance.HITOKOTO_ENCODE).first()
            onSuccess(responseEntity)
        })
    }


    fun getHPImageArchive() {
        var idx = Random.nextInt(0, 4)
        request({
            val hpImageArchive =
                iEntrance.getHPImageArchive(ApiConstants.Entrance.HP_IMAGE_ARCHIVE, "js", idx, 1)
                    .first()
            val hpImageArchiveEntity = hpImageArchive.data as HPImageArchiveEntity
            archiveImagesEntity.set(hpImageArchiveEntity.images[0])
            onSuccess(hpImageArchive)
        })
    }
}