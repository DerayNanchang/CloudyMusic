package com.pmisy.roomkb.ui.viewmodel

import android.text.TextUtils
import com.google.gson.Gson
import com.lsn.comm.core.viewmodel.BaseCoreViewModel
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.login.LoginRequEntity
import com.pmisy.roomkb.entity.login.UserInfEntity
import com.pmisy.roomkb.exts.putResponseData
import com.pmisy.roomkb.manager.SPManager
import com.pmisy.roomkb.repository.net.i.ICommRepository
import com.pmisy.roomkb.repository.net.i.ILoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 04:00
 * @Description :
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    val iLoginRepository: ILoginRepository,
    val iCommRepository: ICommRepository
) : BaseCoreViewModel() {

//    var versionData: ObservableField<VersionEntity?> =
//        ObservableField<VersionEntity?>()

    fun getVersionInfo(
        clientNo: String,
        apkType: String,
    ) {
        if (TextUtils.isEmpty(clientNo)) {
            return
        }
        request({
            val entityFlow = iCommRepository.getVersionInfo(
                ApiConstants.Comm.SEARCH_APK_INFO, clientNo, apkType
            ).first()
//            val versionEntity = entityFlow.data as VersionEntity
//            versionData.set(versionEntity)
            onSuccess(entityFlow)
        })
    }

    fun login(data: LoginRequEntity) {
        val gson = Gson()
        request({
            val entityFlow = iLoginRepository.login(
                ApiConstants.Comm.LOGIN, gson.toJson(data)
            ).first()
            // 保存用户信息
            entityFlow.putResponseData(gson, data.password)
            // 返回给前端结果
            onSuccess(entityFlow)
        })
    }


    fun getCacheUserData(): UserInfEntity? {
        val loginInfoStr = SPManager.instance.getLoginInfo()
        if (TextUtils.isEmpty(loginInfoStr)) {
            return null
        }
        loginInfoStr?.let {
            try {
                val gson = Gson()
                return gson.fromJson(it, UserInfEntity::class.java)
            } catch (e: Exception) {
                return null
            }
        }
        return null
    }

}