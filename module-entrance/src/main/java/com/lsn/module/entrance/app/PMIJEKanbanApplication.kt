package com.pmisy.roomkb.app

import androidx.databinding.library.baseAdapters.BR
import com.lsn.comm.core.app.BaseCoreApplication
import com.lsn.lib.ui.widget.rv.utils.BRV
import com.pmisy.roomkb.Constants
import com.pmisy.roomkb.Constants.EQ.Companion.MACHINE_STATUS_KEY
import com.pmisy.roomkb.entity.login.UserInfEntity
import dagger.hilt.android.HiltAndroidApp


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:31
 * @Description :
 */
@HiltAndroidApp
class PMIJEKanbanApplication : BaseCoreApplication() {

    private var token: String? = null
    private var userInfo: UserInfEntity? = null
    private var clientId: String = ""

    var eqStatusDescHashMap = HashMap<String, String?>()
    var eqStatusColorHashMap = HashMap<String, String?>()

    companion object {
        var DEF = ""
        lateinit var application: PMIJEKanbanApplication
    }

    override fun onCreate() {
        super.onCreate()
        BRV.modelId = BR.item
        application = this


        for (i in 0 until MACHINE_STATUS_KEY.size) {
            eqStatusDescHashMap[MACHINE_STATUS_KEY[i]] = Constants.EQ.MACHINE_DESC_STATUS[i]
            eqStatusColorHashMap[MACHINE_STATUS_KEY[i]] = Constants.EQ.MACHINE_COLOR_STATUS[i]
        }

    }

    fun setClientId(client: String) {
        this.clientId = clientId
    }

    fun getClientId(): String {
        return clientId
    }


    fun getIdentity(): String {
        return clientId + "_" + getUserNo()
    }


    open fun getUserNo(): String {
        if (getUserInfo() == null) {
            return DEF
        }
        return getUserInfo()!!.userNo
    }

    open fun getUserInfo(): UserInfEntity? {
        return userInfo
    }

    open fun setUserInfo(userInfo: UserInfEntity?) {
        this.userInfo = userInfo
    }

    open fun setToken(token: String?) {
        this.token = token
    }

    open fun getToken(): String? {
        return token
    }

}