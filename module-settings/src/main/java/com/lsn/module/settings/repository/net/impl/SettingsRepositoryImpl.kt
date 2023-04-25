package com.lsn.module.settings.repository.net.impl

import com.lsn.module.settings.net.client.SettingsClient
import com.lsn.module.settings.repository.net.i.ISettingsRepository
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class SettingsRepositoryImpl @Inject constructor(var entranceClient: SettingsClient) :
    ISettingsRepository {

}