package com.lsn.module.mine.repository.net.impl

import com.lsn.module.mine.net.client.MineClient
import com.lsn.module.mine.repository.net.i.ISettingsRepository
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class SettingsRepositoryImpl @Inject constructor(var entranceClient: MineClient) :
    ISettingsRepository {

}