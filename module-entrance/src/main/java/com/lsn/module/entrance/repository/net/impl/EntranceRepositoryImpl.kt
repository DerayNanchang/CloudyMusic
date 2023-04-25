package com.lsn.module.entrance.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.net.core.annotation.NetBaseUrlFunc
import com.lsn.lib.net.core.annotation.NetResponseFunc
import com.lsn.module.entrance.api.ApiConstants
import com.lsn.module.entrance.net.client.EntranceClient
import com.lsn.module.entrance.repository.net.i.IEntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class EntranceRepositoryImpl @Inject constructor(var entranceClient: EntranceClient) :
    IEntranceRepository {

    override suspend fun getHitokotoEncode(tag: String): Flow<ResponseEntity> {
        return flowTranData(tag, entranceClient.getHitokotoEncode())
    }

    override suspend fun getHPImageArchive(
        tag: String, format: String, idx: Int, n: Int
    ): Flow<ResponseEntity> {
        val hpImageArchive = entranceClient.getHPImageArchive(format, idx, n)
        if (hpImageArchive.images.isNotEmpty()) {
            val hpImageArchiveImagesEntity = hpImageArchive.images[0]
            val copyrightList = hpImageArchiveImagesEntity.copyright.split("(")
            if (copyrightList.size == 2) {
                val desc = copyrightList[0]
                hpImageArchiveImagesEntity.desc = desc + " ʕ̢̣·͡˔·ོɁ̡̣"
                val mCopyright = copyrightList[1].replace(")", "")
                hpImageArchiveImagesEntity.mICopyright = mCopyright
            }
            val url = ApiConstants.OrderBaseApis.BING + hpImageArchiveImagesEntity.url
            val replace1 = url.replace("www.bing.com//", "www.bing.com/")
            val replace2 = replace1.replace("1920x1080", "1080x1920")
            hpImageArchiveImagesEntity.url = replace2
        }
        return flowTranData(tag, hpImageArchive)
    }
}