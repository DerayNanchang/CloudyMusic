package com.lsn.module.graphic.repository.net.impl

import com.lsn.module.graphic.net.client.GraphicClient
import com.lsn.module.graphic.repository.net.i.IGraphicRepository
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class GraphicRepositoryImpl @Inject constructor(var entranceClient: GraphicClient) :
    IGraphicRepository {

}