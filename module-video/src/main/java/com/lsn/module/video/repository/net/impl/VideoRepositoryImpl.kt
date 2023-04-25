package com.lsn.module.video.repository.net.impl

import com.lsn.module.video.net.client.VideoClient
import com.lsn.module.video.repository.net.i.IVideoRepository
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class VideoRepositoryImpl @Inject constructor(var entranceClient: VideoClient) :
    IVideoRepository {

}