package com.pmisy.roomkb.exts

import com.google.gson.Gson
import com.lsn.comm.core.net.ResponseEntity
import com.pmisy.roomkb.app.PMIJEKanbanApplication
import com.pmisy.roomkb.entity.login.LoginRespEntity
import com.pmisy.roomkb.manager.SPManager

/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 08:43
 * @Description :
 */



fun ResponseEntity.putResponseData(gson: Gson, password:String){
    val respEntity = this.data as LoginRespEntity
    respEntity.userInf?.password = password
    PMIJEKanbanApplication.application.setToken(respEntity.token)
    PMIJEKanbanApplication.application.setUserInfo(respEntity.userInf)
    SPManager.instance.putToken(respEntity.token)
    respEntity?.also {
        SPManager.instance.putLoginInfo(gson.toJson(it.userInf))
    }
}


